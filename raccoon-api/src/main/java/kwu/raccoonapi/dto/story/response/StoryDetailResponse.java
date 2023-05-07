package kwu.raccoonapi.dto.story.response;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class StoryDetailResponse {
    private String storyImageUrl;
    private String contents;
    private Long userId;
    private String nickname;
    private Long age;
    private Double distance;
    private Long likeCount;
    private Boolean likeStatus;

    public static StoryDetailResponse of(Story story, Double distance, Boolean likeStatus){
        StoryDetailResponse storyDetailResponse = new StoryDetailResponse();

        storyDetailResponse.storyImageUrl=story.getStoryImageUrl();
        storyDetailResponse.contents=story.getContents();
        storyDetailResponse.userId=story.getUserProfile().getId();
        storyDetailResponse.nickname=story.getUserProfile().getNickname();
        storyDetailResponse.age=story.getUserProfile().getAge();
        storyDetailResponse.distance= distance;
        storyDetailResponse.likeCount=Integer.toUnsignedLong(story.getLikes().getCounts());
        storyDetailResponse.likeStatus=likeStatus;

        return storyDetailResponse;
    }
}
