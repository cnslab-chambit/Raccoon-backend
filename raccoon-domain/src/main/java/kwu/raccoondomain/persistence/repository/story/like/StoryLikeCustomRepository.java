package kwu.raccoondomain.persistence.repository.story.like;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.Like;
import kwu.raccoondomain.persistence.domain.user.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StoryLikeCustomRepository {
    Optional<Like> storyLikeExist(UserProfile userProfile, Story story);
    Map<Long,Long> countPerStory(List<Story> stories);
}
