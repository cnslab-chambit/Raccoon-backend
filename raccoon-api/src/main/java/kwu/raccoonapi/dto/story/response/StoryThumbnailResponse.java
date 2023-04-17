package kwu.raccoonapi.dto.story.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StoryThumbnailResponse {
    private Long id;
    private String storyImageUrl;
    private Long likeCount;
}