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
    private String profileImageUrl;
    private Double distance;

    public static StoryDetailResponse of(Story story, double distance){
        StoryDetailResponse storyDetailResponse = new StoryDetailResponse();

        storyDetailResponse.storyImageUrl=story.getStoryImageUrl();
        storyDetailResponse.contents=story.getContents();
        storyDetailResponse.userId=story.getUserProfile().getId();
        storyDetailResponse.nickname=story.getUserProfile().getNickname();
        storyDetailResponse.age=story.getUserProfile().getAge();
        storyDetailResponse.profileImageUrl=story.getUserProfile().getProfileImageUrl();
        storyDetailResponse.distance= distance;

        return storyDetailResponse;
    }
}
