package kwu.raccoonapi.controller.ui;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class KakaoAuthController {

    private String kakaoUrl;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    private String responseType = "code";

    private String authRequestUrl;

    @PostConstruct
    private void initRequestUrl(){
        kakaoUrl = "https://kauth.kakao.com";
        UriComponents uri = UriComponentsBuilder.fromUriString(kakaoUrl)
                .pathSegment("oauth", "authorize")
                .queryParam("client_id",clientId)
                .queryParam("redirect_uri",redirectUrl)
                .queryParam("response_type",responseType)
                .build();
        authRequestUrl = uri.toString();
    }

    @GetMapping("/api/v1/oauth/kakao")
    public void redirect(HttpServletResponse response) throws IOException{
        System.out.println(authRequestUrl);
        response.sendRedirect(authRequestUrl);
    }
}
