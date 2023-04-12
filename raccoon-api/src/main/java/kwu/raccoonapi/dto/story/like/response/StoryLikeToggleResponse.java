package kwu.raccoonapi.dto.story.like.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class StoryLikeToggleResponse {
    private Boolean likeStatus;
    private Long likeCount;

    public static StoryLikeToggleResponse of(Long likeCount, Boolean likeStatus){
        StoryLikeToggleResponse storyLikeToggleResponse = new StoryLikeToggleResponse();
        storyLikeToggleResponse.likeCount= likeCount;
        storyLikeToggleResponse.likeStatus=likeStatus;
        return storyLikeToggleResponse;
    }
}
