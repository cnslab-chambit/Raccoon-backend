package kwu.raccoonapi.dto.story.like.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StoryLikeCountResponse {
    private Long likeCount;
}
