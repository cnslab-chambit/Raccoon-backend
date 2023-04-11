package kwu.raccoonapi.controller.api.story.like;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.like.request.StoryLikeToggleRequest;
import kwu.raccoonapi.dto.story.like.response.StoryLikeToggleResponse;
import kwu.raccoonapi.facade.story.like.StoryLikeFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoryLikeController {

    private final StoryLikeFacadeService storyLikeFacadeService;

    @PatchMapping("/story/like")
    public ApiResponse<StoryLikeToggleResponse> toggleStoryLike(@RequestBody StoryLikeToggleRequest request){
        return ApiResponse.ok(storyLikeFacadeService.toggleStoryLike(request));
    }
}
