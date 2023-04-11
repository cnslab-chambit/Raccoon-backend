package kwu.raccoonapi.dto.story.like.request;

import kwu.raccoondomain.dto.story.like.storyLikeToggleDto;
import lombok.Data;
@Data
public class StoryLikeToggleRequest {
    private Long storyId;
    public storyLikeToggleDto toStoryLikeToggleDto() {
        return storyLikeToggleDto.of(
                storyId
        );
    }
}