package kwu.raccoonapi.dto.story.request;

import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
public class StoryUpdateRequest {
    private Long storyId;
    private String contents;
    private MultipartFile storyImageUrl;

    public StoryUpdateDto toStoryUpdateDto(){
        return StoryUpdateDto.of(
                storyId,
                contents,
                storyImageUrl
        );
    }
}
