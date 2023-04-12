package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class userDeleteController {
    private final UserProfileFacadeService userProfileFacadeService;

    @DeleteMapping("/user/profile")
    public ApiResponse<UserProfileUpdateResponse> deleteProfile(@ModelAttribute UserProfileUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfile(request));
    }
}
