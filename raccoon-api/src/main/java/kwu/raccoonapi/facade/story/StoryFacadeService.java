package kwu.raccoonapi.facade.story;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryDetailResponse;
import kwu.raccoonapi.dto.story.response.StoryResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.assembler.StoryAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.service.story.StoryDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryFacadeService {

    private final StoryDomainService storyDomainService;
    private final StoryAssembler storyAssembler;

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

    @Transactional(readOnly = true)
    public List<StoryResponse> getAllStory() {
        List<Story> allStory= storyDomainService.findAllStory();
        return allStory.stream()
                .map(story->storyAssembler.toAllStoryResponse(story))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long StoryId) {
        UserProfile userProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        storyDomainService.deleteStoryByIdOrElseThrow(userProfile, StoryId);
    }

    @Transactional(readOnly = true)
    public StoryDetailResponse getStoryDetail(Long storyId){
        Story story= storyDomainService.getStoryById(storyId);
        return storyAssembler.toStoryDetailResponse(story);
    }

}