package kwu.raccoondomain.persistence.repository.story.like;

import kwu.raccoondomain.persistence.domain.story.StoryLike;

import java.util.Optional;

public interface StoryLikeCustomRepository {
    Optional<StoryLike> storyLikeExist(Long userId, Long storyId);
}
