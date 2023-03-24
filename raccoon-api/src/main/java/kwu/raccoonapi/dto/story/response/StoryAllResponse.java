package kwu.raccoonapi.dto.story.response;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class StoryAllResponse {
    private Long id;
    private String storyImageUrl;
}
