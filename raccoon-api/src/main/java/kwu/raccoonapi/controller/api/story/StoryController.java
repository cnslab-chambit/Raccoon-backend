package kwu.raccoonapi.controller.api.story;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryDetailResponse;
import kwu.raccoonapi.dto.story.response.StoryResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.StoryFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoryController {

    private final StoryFacadeService storyFacadeService;

    @PatchMapping("/story")
    public ApiResponse<StoryUpdateResponse> updateStory(@ModelAttribute StoryUpdateRequest request){
        return ApiResponse.ok(storyFacadeService.updateStory(request));
    }

    @PostMapping("/story")
    public ApiResponse<StoryCreateResponse> createStory(@ModelAttribute StoryCreateRequest request){
        return ApiResponse.ok(storyFacadeService.create(request));
    }

    @GetMapping("/story/all")
    public ApiResponse<List<StoryResponse>> getAllStories(){
        return ApiResponse.ok(storyFacadeService.getAllStory());
    }

    @DeleteMapping("/story/{storyId}")
    public void deleteStory(@PathVariable Long storyId){
        storyFacadeService.delete(storyId);
    }

    @GetMapping("/story/{storyId}")
    public ApiResponse<StoryDetailResponse> getStoryDetail(@PathVariable Long storyId){
        return ApiResponse.ok(storyFacadeService.getStoryDetail(storyId));
    }


}
