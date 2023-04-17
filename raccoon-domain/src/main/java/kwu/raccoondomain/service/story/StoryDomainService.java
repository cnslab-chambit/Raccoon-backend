package kwu.raccoondomain.service.story;

import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.StoryRepository;
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

    public Long updateStory(UserProfile userProfile, StoryUpdateDto storyUpdateDto){
        Story story=storyRepository.findById(storyUpdateDto.getStoryId()).orElseThrow(()->new RaccoonException(RetConsts.ERR602));
        if (story.getUserProfile()!=userProfile){
            throw new RaccoonException(RetConsts.ERR601);
        }
        String storyImgUrl = "";
        if(storyUpdateDto.getStoryImage()!=null){
            storyImgUrl=s3Service.upload(storyUpdateDto.getStoryImage());
        }
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

    public void deleteStoryByIdOrElseThrow(UserProfile userProfile,Long storyId){
        Story story=storyRepository.findById(storyId).orElseThrow(()->new RaccoonException(RetConsts.ERR602));
        if(story.getUserProfile()!=userProfile){
            throw new RaccoonException(RetConsts.ERR601);
        }
        try{
            storyRepository.deleteById(storyId);
        }catch (Exception e) {
            throw new RaccoonException(RetConsts.ERR602);
        }
    }

    public Story getStoryById(Long storyId){
        Story story = storyRepository.findById(storyId).orElseThrow(() -> new RaccoonException(RetConsts.ERR602));
        return story;
    }

}
