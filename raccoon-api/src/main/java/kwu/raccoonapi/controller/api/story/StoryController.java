package kwu.raccoonapi.controller.api.story;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryDetailResponse;
import kwu.raccoonapi.dto.story.response.StoryThumbnailResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.StoryFacadeService;
import kwu.raccooncommon.consts.CommonConsts;
import kwu.raccoondomain.persistence.repository.utils.CursorPageable;
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
    @DeleteMapping("/story/{storyId}")
    public void deleteStory(@PathVariable Long storyId){
        storyFacadeService.delete(storyId);
    }

    @GetMapping("/story/{storyId}")
    public ApiResponse<StoryDetailResponse> getStoryDetail(@PathVariable Long storyId){
        return ApiResponse.ok(storyFacadeService.getStoryDetail(storyId));
    }
    
    // 최신 순 조회
    @GetMapping("/story")
    public ApiResponse<List<StoryThumbnailResponse>> paginateStories(
        @RequestParam Long cursor,
        @RequestParam(required = false,defaultValue = CommonConsts.PAGE_DEFAULT) Long limit,
        @RequestParam(required = false,defaultValue = "id") String sortBy,
        @RequestParam(required = false,defaultValue = "ASC") String order
        ){
        return ApiResponse.ok(storyFacadeService.paginate(CursorPageable.of(cursor,limit,sortBy,order)));
    }

    // 내 게시글 최신 순으로 조회
    @GetMapping("/story/my")
    public ApiResponse<List<StoryThumbnailResponse>> paginateMyStories(
            @RequestParam Long cursor,
            @RequestParam(required = false,defaultValue = CommonConsts.PAGE_DEFAULT) Long limit,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "ASC") String order){
        return ApiResponse.ok(storyFacadeService.paginateMyStory(CursorPageable.of(cursor,limit,sortBy,order)));
    }
}
