package kwu.raccoonapi.dto.story.response;

import kwu.raccoondomain.persistence.domain.story.Story;
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
    private Double x;
    private Double y;
    private String profileImageUrl;

    public static StoryDetailResponse of(Story story){
        StoryDetailResponse storyDetailResponse = new StoryDetailResponse();

        storyDetailResponse.storyImageUrl=story.getStoryImageUrl();
        storyDetailResponse.contents=story.getContents();
        storyDetailResponse.userId=story.getUserProfile().getId();
        storyDetailResponse.nickname=story.getUserProfile().getNickname();
        storyDetailResponse.age=story.getUserProfile().getAge();
        storyDetailResponse.x=story.getUserProfile().getY();
        storyDetailResponse.profileImageUrl=story.getUserProfile().getProfileImageUrl();

        return storyDetailResponse;
    }
}
