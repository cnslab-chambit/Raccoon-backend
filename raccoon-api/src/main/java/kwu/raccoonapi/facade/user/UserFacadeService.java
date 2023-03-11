package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.UserSignUpResponse;
import kwu.raccooncommon.dto.OauthResponse;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import kwu.raccoondomain.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacadeService {
    private final UserDomainService userDomainService;


    public <T extends OauthResponse> UserSignUpResponse signupWithOauth(String code, VendorType vendorType){
        T account = userDomainService.getOauthProfile(code,vendorType);
        System.out.println(account+"마마마마");

        return null;
    }
}
