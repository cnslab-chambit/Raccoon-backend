package kwu.raccoondomain.dto.story;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor(staticName="of")
public class StoryUpdateDto {
    private Long storyId;
    private String contents;
    private MultipartFile storyImage;
}
