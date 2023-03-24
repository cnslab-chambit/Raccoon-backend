package kwu.raccoonapi.facade.story;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.request.StoryUpdateRequest;
import kwu.raccoonapi.dto.story.response.StoryAllResponse;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.dto.story.response.StoryUpdateResponse;
import kwu.raccoonapi.facade.story.assembler.StoryAssembler;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.service.story.StoryDomainService;
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

    @Transactional
    public StoryUpdateResponse updateStory(StoryUpdateRequest request){
        Long storyId = storyDomainService.updateStory(request.toStoryUpdateDto().getStoryId(),request.toStoryUpdateDto());
        return storyAssembler.toStoryUpdateResponse(storyId);

    }
    @Transactional
    public StoryCreateResponse create(StoryCreateRequest request){
        Story story = storyDomainService.create(storyAssembler.toStoryCreateDto(request));
        return storyAssembler.toStoryCreateResponse(story);
    }

    @Transactional(readOnly = true)
    public List<StoryAllResponse> getAllStory() {
        List<Story> allStory= storyDomainService.findAllStory();
        return allStory.stream()
                .map(story->storyAssembler.toAllStoryResponse(story))
                .collect(Collectors.toList());
    }

}