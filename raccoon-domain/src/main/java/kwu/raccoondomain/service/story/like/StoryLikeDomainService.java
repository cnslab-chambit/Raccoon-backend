package kwu.raccoondomain.service.story.like;

import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.StoryLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.query.story.like.StoryLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StoryLikeDomainService {

    public static final Integer INCRESE = 1;
    public static final Integer DECRESE = -1;

    private final StoryLikeRepository storyLikeRepository;

    public StoryLikeDomainService(StoryLikeRepository storyLikeRepository) {
        this.storyLikeRepository = storyLikeRepository;
    }

    public Boolean toggleStoryLike(UserProfile userProfile, Story story) {
        Long userId = userProfile.getId();
        Long storyId = story.getId();
        Optional<StoryLike> optionalStoryLike = storyLikeRepository.storyLikeExist(userId, storyId);
        if (optionalStoryLike.isPresent()) {
            StoryLike storyLike = optionalStoryLike.get();
            storyLikeRepository.delete(storyLike);
            story.updateLikeCount(story.getLikeCount() +DECRESE);
            return false;
        } else {
            StoryLike storyLike = StoryLike.builder().story(story).userProfile(userProfile).build();
            storyLikeRepository.save(storyLike);
            story.updateLikeCount(story.getLikeCount() +INCRESE);
            return true;
        }
    }
}

