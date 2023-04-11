package kwu.raccoondomain.dto.story.like;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="of")
public class storyLikeToggleDto {
    private Long storyId;

}