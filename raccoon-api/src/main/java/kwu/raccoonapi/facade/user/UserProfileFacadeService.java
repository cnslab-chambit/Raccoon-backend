package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileGetResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.assembler.UserProfileAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    //User 반환하면 안되고 DTO로 바꿔야 한다.
    public UserProfileGetResponse getResponse(Long request){
        Long userId= userProfileDomainService.getProfile(request);
        return userProfileAssembler.toUserProfileGetResponse(userId);
    }

}
