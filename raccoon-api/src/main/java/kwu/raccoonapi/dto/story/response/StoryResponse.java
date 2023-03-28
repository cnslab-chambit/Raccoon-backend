package kwu.raccoonapi.dto.story.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StoryResponse {
    private Long id;
    private String storyImageUrl;
}