package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileAllResponse;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import kwu.raccoonapi.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileFacadeService userProfileFacadeService;

    @PatchMapping("/user/profile")
    public ApiResponse<UserProfileUpdateResponse> updateProfile(@ModelAttribute UserProfileUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfile(request));
    }
    
    @GetMapping(path="/user/{userId}")
    public ApiResponse<UserProfileDetailsResponse> getProfile(@PathVariable Long userId){
        return ApiResponse.ok(userProfileFacadeService.getProfile(userId));
    }

    @GetMapping("/user/profile/all")
    public ApiResponse<List<UserProfileAllResponse>> getAllProfiles(){
        return ApiResponse.ok(userProfileFacadeService.getAllProfile());
    }

}
