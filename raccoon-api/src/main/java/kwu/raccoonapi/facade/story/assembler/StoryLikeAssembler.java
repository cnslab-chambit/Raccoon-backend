package kwu.raccoonapi.facade.story.assembler;

import kwu.raccoonapi.dto.story.like.response.StoryLikeCountResponse;
import kwu.raccoonapi.dto.story.like.response.StoryLikeToggleResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import org.springframework.stereotype.Component;

@Component
public class StoryLikeAssembler {
    public StoryLikeToggleResponse toStoryLikeToggleResponse(Long likeCnt, Boolean likeStatus){
        return StoryLikeToggleResponse.of(likeCnt,likeStatus);
    }
    public StoryLikeCountResponse toStoryLikeCountResponse(Long likeCnt){
        return StoryLikeCountResponse.of(likeCnt);
    }
}
