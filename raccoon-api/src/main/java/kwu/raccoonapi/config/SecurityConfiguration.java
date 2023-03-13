package kwu.raccoonapi.config;

import kwu.raccoonapi.config.jwt.JwtAuthenticationEntryPoint;
import kwu.raccoonapi.config.jwt.JwtAuthenticationFilter;
import kwu.raccoonapi.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final String SIGNUP_URL = "/api/v1/oauth/**";
    private final String PING_PONG_URL = "/ping";
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .antMatchers(GET,PING_PONG_URL)
                .antMatchers(SIGNUP_URL);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .userDetailsService(customUserDetailsService)
                .addFilterBefore(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()
                        , jwtAuthenticationEntryPoint
                        ,jwtProvider)
                        ,BasicAuthenticationFilter.class)
                .build();
    }


}
