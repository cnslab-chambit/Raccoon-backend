package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRecommendController {
    private final UserProfileFacadeService userProfileFacadeService;

    @GetMapping("/user/recommend")
    public ApiResponse<List<UserProfileDetailsResponse>> getRecommendation(){
        return ApiResponse.ok(userProfileFacadeService.getRecommendation());
    }
}
