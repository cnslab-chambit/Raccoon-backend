package kwu.raccoonapi.facade.story;

import kwu.raccoonapi.dto.story.request.StoryCreateRequest;
import kwu.raccoonapi.dto.story.response.StoryCreateResponse;
import kwu.raccoonapi.facade.story.assembler.StoryAssembler;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.service.story.StoryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryFacadeService {

    private final StoryDomainService storyDomainService;
    private final StoryAssembler storyAssembler;

    @Transactional
    public StoryCreateResponse create(StoryCreateRequest request){
        Story story = storyDomainService.create(storyAssembler.toStoryCreateDto(request));
        return storyAssembler.toStoryCreateResponse(story);
    }
}
