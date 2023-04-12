package kwu.raccoonapi.facade.story.like.assembler;

import kwu.raccoonapi.dto.story.like.response.StoryLikeToggleResponse;
import org.springframework.stereotype.Component;

@Component
public class StoryLikeAssembler {
    public StoryLikeToggleResponse toStoryLikeToggleResponse(Long likeCnt, Boolean likeStatus){
        return StoryLikeToggleResponse.of(likeCnt,likeStatus);
    }
}
