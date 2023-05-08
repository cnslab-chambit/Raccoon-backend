package kwu.raccoondomain.unit.service;

import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.StoryQueryRepository;
import kwu.raccoondomain.persistence.repository.story.StoryRepository;
import kwu.raccoondomain.service.story.StoryDomainService;
import kwu.raccoondomain.service.story.StoryLikeDomainService;
import kwu.raccoondomain.unit.fixture.FileFactory;
import kwu.raccoondomain.unit.fixture.story.StoryFactory;
import kwu.raccoondomain.unit.fixture.user.userprofile.UserProfileFactory;
import kwu.raccooninfra.service.s3.S3Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StoryDomainServiceTest {

    @InjectMocks
    private StoryDomainService storyDomainService;

    @Mock
    private StoryRepository storyRepository;

    @Mock
    private S3Service s3Service;

    @Mock
    StoryQueryRepository storyQueryRepository;

    @DisplayName("사용자는 스토리를 등록할 수 있따.")
    @Test
    void create_Story_LoginUser(){
        // given
        UserProfile userProfile = UserProfileFactory.userProfile(1L);
        StoryCreateDto dto = StoryCreateDto.of(userProfile,"testContent", FileFactory.getTestImage1());

        Story story = Story.of(userProfile,extractImageUrlFrom(dto),dto.getContents());

        given(storyRepository.save(any(Story.class))).willReturn(story);

        // when
        Story ret = storyDomainService.create(dto);

        // then
        assertThat(ret).isNotNull();


        verify(storyRepository,times(1))
                .save(any(Story.class));
        
    }


    private String extractImageUrlFrom(StoryCreateDto storyCreateDto){
        return String.format("https://testImage./%s",storyCreateDto.getStoryImage().getName());
    }
}
