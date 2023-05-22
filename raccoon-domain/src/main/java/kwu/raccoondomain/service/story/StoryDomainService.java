package kwu.raccoondomain.service.story;

import io.lettuce.core.GeoArgs;
import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.StoryQueryRepository;
import kwu.raccoondomain.persistence.repository.story.StoryRepository;
import kwu.raccoondomain.persistence.repository.utils.CursorPageable;
import kwu.raccooninfra.service.rabbitmq.UserLikeService;
import kwu.raccooninfra.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoryDomainService {
    private final StoryRepository storyRepository;
    private final S3Service s3Service;
    private final StoryQueryRepository storyQueryRepository;
    private final UserLikeService userLikeService;
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

        Story story = Story.of(dto.getUserProfile(),imageUrl,dto.getContents());

        storyRepository.save(story);
        return story;
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

    public List<Story> paginate(CursorPageable<Long> cursorPageable){
        Sort sort = Sort.by(getOrder(cursorPageable.getOrder()), cursorPageable.getSortBy());
        Pageable pageable = PageRequest.of(cursorPageable.getCursor().intValue(),cursorPageable.getLimit().intValue(),sort);
        return storyQueryRepository.paginate(pageable);
    }

    public List<Story> paginateMyStory(CursorPageable<Long> cursorPageable, Long userId) {
        Sort sort = Sort.by(getOrder(cursorPageable.getOrder()), cursorPageable.getSortBy());
        Pageable pageable = PageRequest.of(cursorPageable.getCursor().intValue(),cursorPageable.getLimit().intValue(),sort);
        return storyQueryRepository.paginateMyStory(pageable,userId);
    }

    private Sort.Direction getOrder(String order){
        return order == "ASC" ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

}
