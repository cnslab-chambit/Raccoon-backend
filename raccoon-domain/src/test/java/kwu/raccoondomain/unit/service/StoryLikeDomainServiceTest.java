package kwu.raccoondomain.unit.service;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.Like;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.like.StoryLikeRepository;
import kwu.raccoondomain.service.story.StoryLikeDomainService;
import kwu.raccoondomain.unit.fixture.story.StoryFactory;
import kwu.raccoondomain.unit.fixture.user.userprofile.UserProfileFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StoryLikeDomainServiceTest {

    @InjectMocks
    private StoryLikeDomainService storyLikeDomainService;

    @Mock
    private StoryLikeRepository storyLikeRepository;

    private Story story1;
    private Story story2;

    @BeforeEach
    void setUp(){
        story1 = StoryFactory.mockStory();
    }

    @DisplayName("사용자의 좋아요 요청")
    @Nested
    public class story_like{
        @DisplayName("좋아요 안한 상태라면 좋아요 등록")
        @Test
        void like(){
            // given
            UserProfile userProfile = UserProfileFactory.userProfile(1L);
            Story story = StoryFactory.mockStory();

            given(storyLikeRepository.storyLikeExist(userProfile,story)).willReturn(Optional.ofNullable(null));

            // when
            Boolean likeStatus = storyLikeDomainService.toggleStoryLike(userProfile,story);

            // then
            assertThat(likeStatus).isTrue();
            assertThat(story.getLikes().getLikes().stream().filter(like -> like.getStory()==story &&
                    like.getUserProfile()==userProfile).findAny()).isNotEmpty();

            verify(storyLikeRepository,times(1))
                    .storyLikeExist(userProfile,story);
        }

        @DisplayName("좋아요 한 상태라면 좋아요 해제")
        @Test
        void unlike(){
            // given
            UserProfile userProfile = UserProfileFactory.userProfile(1L);
            Story story = StoryFactory.mockStory();

            Like like = new Like(story,userProfile);

            story.like(userProfile);

            given(storyLikeRepository.storyLikeExist(userProfile,story)).willReturn(Optional.of(like));

            // when
            Boolean likeStatus = storyLikeDomainService.toggleStoryLike(userProfile,story);

            // then
            assertThat(likeStatus).isFalse();

            verify(storyLikeRepository,times(1))
                    .storyLikeExist(userProfile,story);
        }
    }


}
