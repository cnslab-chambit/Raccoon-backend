package kwu.raccoondomain.service.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.StoryLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.query.story.like.StoryLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StoryLikeDomainService {
    private final StoryLikeRepository storyLikeRepository;

    public StoryLikeDomainService(StoryLikeRepository storyLikeRepository) {
        this.storyLikeRepository = storyLikeRepository;
    }

    public Boolean toggleStoryLike(UserProfile userProfile, Story story) {
        Long userProfileId = userProfile.getId();
        Long storyId = story.getId();
        Optional<StoryLike> optionalStoryLike = storyLikeRepository.storyLikeExist(userProfileId, storyId);
        if (optionalStoryLike.isPresent()) {
            StoryLike storyLike = optionalStoryLike.get();
            storyLikeRepository.delete(storyLike);
            story.decreaseLikeCount();
            return false;
        } else {
            StoryLike storyLike = StoryLike.builder().story(story).userProfile(userProfile).build();
            storyLikeRepository.save(storyLike);
            story.increaseLikeCount();
            return true;
        }
    }

    public Boolean getLikeStatus(UserProfile userProfile, Story story) {
        Long userProfileId = userProfile.getId();
        Long storyId = story.getId();
        Optional<StoryLike> optionalStoryLike = storyLikeRepository.storyLikeExist(userProfileId, storyId);
        if (optionalStoryLike.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}

