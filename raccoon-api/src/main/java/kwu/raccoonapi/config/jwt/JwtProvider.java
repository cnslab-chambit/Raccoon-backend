package kwu.raccoonapi.config.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static kwu.raccooncommon.consts.CommonConsts.AUTH_HEADER;
import static kwu.raccooncommon.consts.CommonConsts.BEARER;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.ttl}")
    private long tokenTTL;

    private final UserDetailsService customUserDetailsService;

    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Long id, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        claims.put("roles", roles);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenTTL))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasText(header) && header.startsWith(BEARER)) {
            return header.substring(7);
        }

        return "";
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(this.getUserIdFromToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails
                , ""
                , userDetails.getAuthorities());
    }


    public boolean validateTokenOrElseThrow(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new RuntimeException();
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }


    private String getUserIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .getSubject();
    }

}
