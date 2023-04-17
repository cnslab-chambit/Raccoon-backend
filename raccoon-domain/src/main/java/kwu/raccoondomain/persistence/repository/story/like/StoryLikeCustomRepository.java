package kwu.raccoondomain.persistence.repository.story.like;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.StoryLike;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StoryLikeCustomRepository {
    Optional<StoryLike> storyLikeExist(Long userId, Long storyId);
    Map<Long,Long> countPerStory(List<Story> stories);
}
