package kwu.raccoonapi.dto.story.response;

import kwu.raccoondomain.dto.story.StoryCreateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoryCreateResponse {
    private Long storyId;
    public static StoryCreateResponse of(Long storyId){
        StoryCreateResponse response = new StoryCreateResponse();
        response.storyId = storyId;
        return response;
    }
}
