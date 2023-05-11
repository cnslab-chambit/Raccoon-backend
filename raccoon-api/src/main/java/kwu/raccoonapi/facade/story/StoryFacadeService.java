package kwu.raccoonapi.facade.story;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryDetailResponse;
import kwu.raccoonapi.dto.story.response.StoryThumbnailResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.assembler.StoryAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.utils.CursorPageable;
import kwu.raccoondomain.service.story.StoryDomainService;
import kwu.raccoondomain.service.story.StoryLikeDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryFacadeService {

    private final StoryDomainService storyDomainService;
    private final StoryAssembler storyAssembler;
    private final StoryLikeDomainService storyLikeDomainService;
    private final UserProfileDomainService userProfileDomainService;

    @Transactional
    public StoryUpdateResponse updateStory(StoryUpdateRequest request){
        UserProfile userProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        Long storyId = storyDomainService.updateStory(userProfile,request.toStoryUpdateDto());
        return storyAssembler.toStoryUpdateResponse(storyId);

    }
    @Transactional
    public StoryCreateResponse create(StoryCreateRequest request){
        Story story = storyDomainService.create(storyAssembler.toStoryCreateDto(request));
        return storyAssembler.toStoryCreateResponse(story);
    }

    @Transactional
    public void delete(Long StoryId) {
        UserProfile userProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        storyDomainService.deleteStoryByIdOrElseThrow(userProfile, StoryId);
    }

    @Transactional(readOnly = true)
    public StoryDetailResponse getStoryDetail(Long storyId){
        Story story = storyDomainService.getStoryById(storyId);
        UserProfile userProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        Double distance = userProfileDomainService.getDistance(story.getUserProfile(), userProfile);
        Boolean likeStatus = storyLikeDomainService.getLikeStatus(userProfile,story);
        return storyAssembler.toStoryDetailResponse(story,distance,likeStatus,story.getUserProfile());
    }

    @Transactional(readOnly = true)
    public List<StoryThumbnailResponse> paginate(CursorPageable<Long> cursorPageable){
        List<Story> stories = storyDomainService.paginate(cursorPageable);
        Map<Long, Long> storyLikeCountPerStory = storyLikeDomainService.getLikeCountPerStory(stories);
        return stories.stream()
                .map(story -> storyAssembler.toAllStoryResponse(story,storyLikeCountPerStory.getOrDefault(story.getId(),0L)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StoryThumbnailResponse> paginateMyStory(CursorPageable<Long> cursorPageable){
        Long userId = userProfileDomainService.getProfile(SecurityUtils.getUser().getId()).getId();
        List<Story> stories = storyDomainService.paginateMyStory(cursorPageable,userId);
        Map<Long, Long> storyLikeCountPerStory = storyLikeDomainService.getLikeCountPerStory(stories);
        return stories.stream()
                .map(story -> storyAssembler.toAllStoryResponse(story,storyLikeCountPerStory.getOrDefault(story.getId(),0L)))
                .collect(Collectors.toList());
    }

}