package kwu.raccoondomain.service.story;

import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.query.story.StoryRepository;
import kwu.raccooninfra.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryDomainService {
    private final StoryRepository storyRepository;
    private final S3Service s3Service;

    public Long updateStory(Long storyId, StoryUpdateDto storyUpdateDto){
        String storyImgUrl=s3Service.upload(storyUpdateDto.getStoryImageUrl());
        Story story=storyRepository.findById(storyId).orElseThrow(()->new RaccoonException(RetConsts.ERR600));
        story.updateStory(storyUpdateDto,storyImgUrl);
        return story.getId();
    }

    public Story create(StoryCreateDto dto){
        String imageUrl = s3Service.upload(dto.getStoryImage());

        Story story = Story.of(dto.getUserProfile(),imageUrl,dto);

        storyRepository.save(story);
        return story;
    }

    public List<Story> findAllStory(){
        List<Story> all = storyRepository.findAll();
        return all;
    }

}
