package kwu.raccooninfra.apis.oauth;

import kwu.raccooninfra.dto.KakaoProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static kwu.raccooncommon.consts.CommonConsts.AUTH_HEADER;

@FeignClient(name = "kakao-profile",url = "https://kapi.kakao.com")
public interface KakaoProfileApi {
    @GetMapping(value = "/v2/user/me", consumes = "application/x-www-form-urlencoded;charset=utf-8")
    KakaoProfileResponse getKakaoUser(@RequestHeader(name = AUTH_HEADER) String accessToken);
}
