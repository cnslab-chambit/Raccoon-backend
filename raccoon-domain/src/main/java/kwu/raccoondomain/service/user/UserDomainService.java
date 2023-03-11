package kwu.raccoondomain.service.user;

import kwu.raccooncommon.dto.OauthResponse;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import kwu.raccooninfra.dto.KakaoProfileResponse;
import kwu.raccooninfra.service.oauth.KakaoInfraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {

    private final KakaoInfraService kakaoInfraService;


    public <T extends OauthResponse> T getOauthProfile(String code, VendorType vendorType){
        
        switch (vendorType){
            case KAKAO:
                return (T) getKakaoProfile(code);
            case NAVER:
                
                
            //TODO exception 정의
            default:
                throw  new RuntimeException();
        }
    }

    private KakaoProfileResponse getKakaoProfile(String code){
        try{
            return kakaoInfraService.getKakaoAccount(kakaoInfraService.getAccessToken(code));
        //TODO exception
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
