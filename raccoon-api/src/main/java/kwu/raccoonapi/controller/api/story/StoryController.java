package kwu.raccoonapi.controller.api.story;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.facade.story.StoryFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StoryController {

    private final StoryFacadeService storyFacadeService;

    @PostMapping("/story")
    public ApiResponse<StoryCreateResponse> create(@ModelAttribute StoryCreateRequest request){
        return ApiResponse.ok(storyFacadeService.create(request));
    }


}
