package kwu.raccoonapi.config.jwt;

import kwu.raccoonapi.exception.RaccoonAuthException;
import kwu.raccooncommon.consts.CommonConsts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kwu.raccooncommon.consts.CommonConsts.JSON_TYPE;
import static kwu.raccooncommon.consts.CommonConsts.UTF_8;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        commence(response,(RaccoonAuthException) authException);
    }
    private void commence(HttpServletResponse response, RaccoonAuthException ex) throws IOException {
        response.setContentType(JSON_TYPE+";"+"charset="+UTF_8);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JSONObject body = new JSONObject();
        body.put("code",ex.getRetConsts().getCode());
        body.put("message",ex.getRetConsts().getDescription());

        response.getWriter().write(body.toJSONString());
    }
}
