package kwu.raccoondomain.service.story.like;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.StoryLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.query.story.like.StoryLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StoryLikeDomainServiceImpl implements StoryLikeDomainService {

    private final StoryLikeRepository storyLikeRepository;

    public StoryLikeDomainServiceImpl(StoryLikeRepository storyLikeRepository) {
        this.storyLikeRepository = storyLikeRepository;
    }

    //스토리 상태 바꾸기
    @Override
    public Boolean toggleStoryLike(UserProfile userProfile, Story story) {
        Long userId = userProfile.getId();
        Long storyId = story.getId();
        Optional<StoryLike> optionalStoryLike = storyLikeRepository.storyLikeExist(userId, storyId);
        if (optionalStoryLike.isPresent()) { //존재하면 StoryLike 객체 삭제
            StoryLike storyLike = optionalStoryLike.get();
            storyLikeRepository.delete(storyLike);
            return false;
        } else { //없었다면 StoryLike 객체 생성
            StoryLike storyLike = StoryLike.builder().story(story).userProfile(userProfile).build();
            storyLikeRepository.save(storyLike);
            return true;
        }
    }

    //스토리 좋아요 수 가져오기
    @Override
    public Integer countStoryLikeNum(Long storyId) {
        return storyLikeRepository.countStoryLikeNum(storyId).intValue();
    }
}

