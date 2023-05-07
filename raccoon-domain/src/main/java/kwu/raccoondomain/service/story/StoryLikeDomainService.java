package kwu.raccoondomain.service.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.Like;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.like.StoryLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StoryLikeDomainService {
    private final StoryLikeRepository storyLikeRepository;

    public StoryLikeDomainService(StoryLikeRepository storyLikeRepository) {
        this.storyLikeRepository = storyLikeRepository;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean toggleStoryLike(UserProfile userProfile, Story story) {
        Optional<Like> optionalStoryLike = storyLikeRepository.storyLikeExist(userProfile, story);
        if (optionalStoryLike.isPresent()) {
            story.unlike(userProfile);
            return false;
        }
        story.like(userProfile);
        return true;
    }

    public Boolean getLikeStatus(UserProfile userProfile, Story story) {
        Optional<Like> optionalStoryLike = storyLikeRepository.storyLikeExist(userProfile,story);
        if (optionalStoryLike.isPresent()) {
            return true;
        }
        return false;
    }

    public Map<Long,Long> getLikeCountPerStory(List<Story> stories){
        return storyLikeRepository.countPerStory(stories);
    }

}

