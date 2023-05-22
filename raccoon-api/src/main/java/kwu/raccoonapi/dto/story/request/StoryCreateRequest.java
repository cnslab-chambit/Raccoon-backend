package kwu.raccoonapi.dto.story.request;

import kwu.raccoonapi.controller.verification.LetterLength;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoryCreateRequest {
    @LetterLength(min=10,max=200,nullable = false)
    private String contents;
    private MultipartFile storyImage;
}
