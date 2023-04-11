package kwu.raccoondomain.service.story.like;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;

public interface StoryLikeDomainService {
    Boolean toggleStoryLike(UserProfile userProfile, Story story);
    Integer countStoryLikeNum(Long storyId);
}