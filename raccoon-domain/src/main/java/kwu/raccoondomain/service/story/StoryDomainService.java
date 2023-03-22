package kwu.raccoondomain.service.story;

import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.query.story.StoryRepository;
import kwu.raccooninfra.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryDomainService {
    private final StoryRepository storyRepository;
    private final S3Service s3Service;
    public Story create(StoryCreateDto dto){
        String imageUrl = s3Service.upload(dto.getStoryImage());

        Story story = Story.of(dto.getUserProfile(),imageUrl,dto);

        storyRepository.save(story);
        return story;
    }
}
