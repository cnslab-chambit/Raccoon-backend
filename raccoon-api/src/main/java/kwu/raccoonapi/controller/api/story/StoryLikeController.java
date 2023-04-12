package kwu.raccoonapi.controller.api.story;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.like.response.StoryLikeCountResponse;
import kwu.raccoonapi.dto.story.like.response.StoryLikeToggleResponse;
import kwu.raccoonapi.facade.story.StoryLikeFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoryLikeController {

    private final StoryLikeFacadeService storyLikeFacadeService;

    @PatchMapping("/story/like/{storyId}")
    public ApiResponse<StoryLikeToggleResponse> toggleStoryLike(@PathVariable Long storyId){
        return ApiResponse.ok(storyLikeFacadeService.toggleStoryLike(storyId));
    }
    @GetMapping("/story/like/{storyId}")
    public ApiResponse<StoryLikeCountResponse> getStoryLikeCount(@PathVariable Long storyId){
        return ApiResponse.ok(storyLikeFacadeService.getStoryLikeCount(storyId));
    }
}