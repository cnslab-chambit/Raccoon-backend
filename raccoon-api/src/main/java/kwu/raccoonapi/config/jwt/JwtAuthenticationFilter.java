package kwu.raccoonapi.config.jwt;

import kwu.raccoonapi.exception.RaccoonAuthException;
import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final JwtProvider jwtProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                   JwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = jwtProvider.extractToken(request);
        try {
            if(!StringUtils.hasText(jwt)){
                throw new RaccoonAuthException(RetConsts.ERR403);
            }
            if(jwtProvider.validateTokenOrElseThrow(jwt)){
                SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(jwt));
                chain.doFilter(request, response);
            }else{
                throw  new RaccoonAuthException(RetConsts.ERR401);
            }
        }catch (RaccoonException e){
            jwtAuthenticationEntryPoint.commence(request,response,new RaccoonAuthException(e.getRetConsts()));
        }catch (RaccoonAuthException e){
            jwtAuthenticationEntryPoint.commence(request,response,e);
        }
    }
}
