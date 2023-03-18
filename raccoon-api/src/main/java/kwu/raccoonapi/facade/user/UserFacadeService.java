package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.config.jwt.JwtProvider;
import kwu.raccoonapi.dto.user.response.UserSignUpResponse;
import kwu.raccoonapi.facade.user.assembler.UserAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccooncommon.dto.OauthResponse;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import kwu.raccoondomain.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFacadeService {
    private final UserDomainService userDomainService;

    private final UserAssembler userAssembler;

    private final JwtProvider jwtProvider;

    public <T extends OauthResponse> UserSignUpResponse signupWithOauth(String code, VendorType vendorType){
        T account = userDomainService.getOauthProfile(code,vendorType);

        Optional<User> optionalUser = userDomainService.getUserByVendorIdAndVendorType(account.getId(), vendorType);

        if(optionalUser.isPresent()){
            return userAssembler.toUserSignUpResponse(optionalUser.get(),getAccessToken(optionalUser.get().getId()),false);
        }

        User user  = signupWithOauth(
                account.getId(),
                vendorType,
                account.getEmail()
        );
        return userAssembler.toUserSignUpResponse(user,getAccessToken(user.getId()),true);
    }

    private String getAccessToken(Long userId){
        return jwtProvider.createToken(userId, List.of());
    }

    private User signupWithOauth(String vendorId, VendorType vendorType, String email){
        return userDomainService.save(userAssembler.toUserSignUpDto(vendorId,vendorType,email));
    }

    public void deleteUser() {
        userDomainService.deleteUserByIdOrElseThrow(SecurityUtils.getUser().getId());
    }
}
