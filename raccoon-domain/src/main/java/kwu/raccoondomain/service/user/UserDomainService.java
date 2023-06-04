package kwu.raccoondomain.service.user;

import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.dto.OauthResponse;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.user.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import kwu.raccoondomain.persistence.repository.user.UserRepository;
import kwu.raccooninfra.dto.KakaoProfileResponse;
import kwu.raccooninfra.service.oauth.KakaoInfraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDomainService {

    private final KakaoInfraService kakaoInfraService;
    private final UserRepository userRepository;

    public <T extends OauthResponse> T getOauthProfile(String code, VendorType vendorType){
        
        switch (vendorType){
            case KAKAO:
                return (T) getKakaoProfile(code);
            case NAVER:

            default:
                throw  new RuntimeException();
        }
    }

    private KakaoProfileResponse getKakaoProfile(String code){
        try{
            return kakaoInfraService.getKakaoAccount(kakaoInfraService.getAccessToken(code));

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Optional<User> getUserByVendorIdAndVendorType(String vendorId,VendorType vendorType){
        return userRepository.findByVendorIdAndVendorType(vendorId,vendorType);
    }

    public User save(UserSignUpDto userSignUpDto){
        return userRepository.save(User.fromSignUpDto(userSignUpDto));
    }


    public User getUserByIdOrElseThrow(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException());
    }

    public void deleteUserByIdOrElseThrow(Long userId){
        try{
            userRepository.deleteById(userId);
        }catch (Exception e) {
            throw new RaccoonException(RetConsts.ERR600);
        }
    }
}
