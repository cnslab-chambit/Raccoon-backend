package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileGetResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.assembler.UserProfileAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserProfileFacadeService {
    private final UserProfileAssembler userProfileAssembler;
    private final UserProfileDomainService userProfileDomainService;
    public UserProfileUpdateResponse updateProfile(UserProfileUpdateRequest request){
        Long userId = userProfileDomainService
                .updateProfile(SecurityUtils.getUser().getId(), request.toUserProfileUpdateDto());
        return userProfileAssembler.toUserProfileUpdateResponse(userId);
    }

    public UserProfileGetResponse getProfile(Long userId){
        UserProfile userProfile= userProfileDomainService.getProfile(userId);
        return userProfileAssembler.toUserProfileResponse(userProfile);
    }

}
