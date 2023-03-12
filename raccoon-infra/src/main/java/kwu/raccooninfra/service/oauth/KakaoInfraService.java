package kwu.raccooninfra.service.oauth;

import kwu.raccooninfra.apis.oauth.KakaoProfileApi;
import kwu.raccooninfra.apis.oauth.KakaoAuthApi;
import kwu.raccooninfra.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static kwu.raccooncommon.consts.CommonConsts.AUTH_CODE;
import static kwu.raccooncommon.consts.CommonConsts.BEARER;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoInfraService {

    private final KakaoProfileApi kakaoProfileApi;
    private final KakaoAuthApi kakaoAuthApi;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.secret}")
    private String clientSecret;

    public KakaoProfileResponse getKakaoAccount(String accessToken){
        System.out.println(accessToken+"accessToken입니다@@@@@@@@@@@@");
        return kakaoProfileApi.getKakaoUser(BEARER.concat(" "+accessToken));
    }

    public String getAccessToken(String code){
        System.out.println(code+"code"+"코드입니다");
        return kakaoAuthApi.getAccessToken(AUTH_CODE,clientId,redirectUrl,code,clientSecret).getAccessToken();
    }

}
