package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.request.UserAnimalUpdateRequest;
import kwu.raccoonapi.dto.user.request.UserCoordinateUpdateRequest;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.*;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileFacadeService userProfileFacadeService;

    @PatchMapping("/user/profile")
    public ApiResponse<UserProfileUpdateResponse> updateProfile(@ModelAttribute @Valid UserProfileUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfile(request));
    }

    @PatchMapping("/user/profile/coordinate")
    public ApiResponse<UserProfileUpdateResponse> updateProfileCoordinate(@RequestBody UserCoordinateUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfileCoordinate(request));
    }

    @PatchMapping("/user/profile/animal")
    public ApiResponse<UserProfileUpdateResponse> updateProfileAnimal(@RequestBody UserAnimalUpdateRequest request){
        return ApiResponse.ok(userProfileFacadeService.updateProfileAnimal(request));
    }

    @GetMapping("/user/profile/{userId}")
    public ApiResponse<UserProfileDetailsResponse> getProfile(@PathVariable Long userId){
        return ApiResponse.ok(userProfileFacadeService.getProfile(userId));
    }

    @GetMapping("/user/profile/my")
    public ApiResponse<UserProfileDetailsResponse> getProfile(){
        return ApiResponse.ok(userProfileFacadeService.getMyProfile());
    }

    @GetMapping("/user/profile/gender")
    public ApiResponse<UserGenderResponse> getUserGender(){
        return ApiResponse.ok(userProfileFacadeService.getUserGender());
    }
}
