package kwu.raccoonapi.controller.api.story;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryAllResponse;
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

    @PostMapping("/story")
    public ApiResponse<StoryCreateResponse> create(@ModelAttribute StoryCreateRequest request){
        return ApiResponse.ok(storyFacadeService.create(request));
    }

    @GetMapping("/story/all")
    public ApiResponse<List<StoryAllResponse>> getAllStories(){
        return ApiResponse.ok(storyFacadeService.getAllStory());
    }

    @PatchMapping("/story")
    public ApiResponse<StoryUpdateResponse> updateStory(@ModelAttribute StoryUpdateRequest request){
        return ApiResponse.ok(storyFacadeService.updateStory(request));
    }

}
