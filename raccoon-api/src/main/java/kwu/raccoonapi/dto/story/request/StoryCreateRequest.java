package kwu.raccoonapi.dto.story.request;

import kwu.raccoonapi.controller.verification.Story;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoryCreateRequest {
    @Story(min=10,max=200,nullable = false)
    private String contents;
    private MultipartFile storyImage;
}
