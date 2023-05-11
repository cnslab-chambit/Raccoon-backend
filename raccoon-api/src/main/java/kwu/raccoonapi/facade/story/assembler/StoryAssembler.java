package kwu.raccoonapi.facade.story.assembler;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.response.StoryDetailResponse;
import kwu.raccoonapi.dto.story.response.StoryThumbnailResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class StoryAssembler {

    public StoryUpdateResponse toStoryUpdateResponse(Long StoryId){
        return StoryUpdateResponse.of(StoryId);
    }

    public StoryCreateDto toStoryCreateDto(StoryCreateRequest request){
        return StoryCreateDto.of(SecurityUtils.getUser().getUserProfile(),
                request.getContents(),
                request.getStoryImage()
                );
    }
    public StoryCreateResponse toStoryCreateResponse(Story story){
        return StoryCreateResponse.of(story.getId());
    }

    public StoryThumbnailResponse toAllStoryResponse(Story story,Long likeCount){
        return StoryThumbnailResponse.of(
                story.getId(),
                story.getStoryImageUrl(),
                likeCount
        );
    }

    public StoryDetailResponse toStoryDetailResponse(Story story, Double distance, Boolean likeStatus, UserProfile userProfile){
        return StoryDetailResponse.of(story,distance,likeStatus,userProfile);
    }
}
