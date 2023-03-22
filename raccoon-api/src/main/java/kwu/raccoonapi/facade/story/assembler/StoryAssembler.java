package kwu.raccoonapi.facade.story.assembler;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryAssembler {

    public StoryCreateDto toStoryCreateDto(StoryCreateRequest request){
        return StoryCreateDto.of(SecurityUtils.getUser().getUserProfile(),
                request.getContents(),
                request.getStoryImage()
                );
    }
    public StoryCreateResponse toStoryCreateResponse(Story story){
        return StoryCreateResponse.of(story.getId());
    }
}
