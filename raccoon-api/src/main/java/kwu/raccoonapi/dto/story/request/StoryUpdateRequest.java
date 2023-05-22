package kwu.raccoonapi.dto.story.request;

import kwu.raccoonapi.controller.verification.Story;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoryUpdateRequest {
    private Long storyId;
    @Story(min=10,max=200,nullable = false)
    private String contents;
    private MultipartFile storyImage;

    public StoryUpdateDto toStoryUpdateDto(){
        return StoryUpdateDto.of(
                storyId,
                contents,
                storyImage
        );
    }
}
