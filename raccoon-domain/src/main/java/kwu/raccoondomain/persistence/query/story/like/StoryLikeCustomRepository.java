package kwu.raccoondomain.persistence.query.story.like;

import kwu.raccoondomain.persistence.domain.story.like.StoryLike;

import java.util.Optional;

public interface StoryLikeCustomRepository {
    Optional<StoryLike> storyLikeExist(Long userId, Long storyId);
    Number countStoryLikeNum(Long storyId);
}
