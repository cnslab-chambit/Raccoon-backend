package kwu.raccoonapi.dto.story.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoryCreateRequest {
    private String contents;
    private MultipartFile storyImage;
}
