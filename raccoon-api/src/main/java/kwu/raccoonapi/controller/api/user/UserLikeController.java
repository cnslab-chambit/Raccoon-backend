package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.user.request.UserLikeRequest;
import kwu.raccoonapi.facade.user.UserLikeFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserLikeController {
    private final UserLikeFacadeService userLikeFacadeService;
    @PostMapping("/user/like")
    public ApiResponse<?> sendUserLike(@RequestBody UserLikeRequest userLikeRequest){
        userLikeFacadeService.sendUserLike(userLikeRequest);
        return ApiResponse.ok();
    }
}
