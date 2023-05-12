package kwu.raccoonapi.facade.story;

import kwu.raccoonapi.dto.story.like.response.StoryLikeCountResponse;
import kwu.raccoonapi.dto.story.like.response.StoryLikeToggleResponse;
import kwu.raccoonapi.facade.story.assembler.StoryLikeAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.service.story.StoryDomainService;
import kwu.raccoondomain.service.story.StoryLikeDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryLikeFacadeService {
    private final StoryLikeAssembler storyLikeAssembler;
    private final StoryLikeDomainService storyLikeDomainService;
    private final UserProfileDomainService userProfileDomainService;
    private final StoryDomainService storyDomainService;

    @Transactional
    public StoryLikeToggleResponse toggleStoryLike(Long storyId) {

        UserProfile userProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        Story story = storyDomainService.getStoryById(storyId);

        Boolean likeStatus = storyLikeDomainService.toggleStoryLike(userProfile, story);
        Long likeCnt = story.getLikeCount();

        return storyLikeAssembler.toStoryLikeToggleResponse(likeCnt, likeStatus);
    }

    @Transactional
    public StoryLikeCountResponse getStoryLikeCount(Long storyId){
        Story story = storyDomainService.getStoryById(storyId);
        Long likeCnt = story.getLikeCount();
        return storyLikeAssembler.toStoryLikeCountResponse(likeCnt);
    }
}
