package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileFacadeService userProfileFacadeService;

    @PatchMapping("/user/profile")
    public ApiResponse<UserProfileUpdateResponse> updateProfile(@ModelAttribute UserProfileUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfile(request));
    }

}
