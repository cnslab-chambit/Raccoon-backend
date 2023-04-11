package kwu.raccoonapi.dto.story.like.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class StoryLikeToggleResponse {
    private Boolean likeStatus; // 해당 유저가 좋아요를 눌렀는지 여부
    private Integer likeCount; // 해당 게시물에 눌린 총 좋아요 수

    public static StoryLikeToggleResponse of(Integer likeCount, Boolean likeStatus){
        StoryLikeToggleResponse storyLikeToggleResponse = new StoryLikeToggleResponse();
        storyLikeToggleResponse.likeCount= likeCount;
        storyLikeToggleResponse.likeStatus=likeStatus;
        return storyLikeToggleResponse;
    }
}
