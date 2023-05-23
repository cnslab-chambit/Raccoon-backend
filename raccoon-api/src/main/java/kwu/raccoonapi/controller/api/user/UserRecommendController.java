package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRecommendController {
    private final UserProfileFacadeService userProfileFacadeService;

    @GetMapping("/user/recommend")
    public void getRecommendation(){
        userProfileFacadeService.getRecommendation();
    }
}
