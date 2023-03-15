package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserOnboardProfileRequest;
import kwu.raccoonapi.dto.user.response.UserOnboardProfileResponse;
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
    public UserOnboardProfileResponse onBoardProfile(UserOnboardProfileRequest request){
        Long userId = userProfileDomainService
                .onBoardProfile(SecurityUtils.getUser().getId(), userProfileAssembler.toUserOnboardProfileDto(request));
        return userProfileAssembler.toUserOnboardProfileResponse(userId);
    }

}
