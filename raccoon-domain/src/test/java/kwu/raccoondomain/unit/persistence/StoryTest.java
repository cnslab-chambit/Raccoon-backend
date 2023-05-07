package kwu.raccoondomain.unit.persistence;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.unit.fixture.story.StoryFactory;
import kwu.raccoondomain.unit.fixture.user.userprofile.UserProfileFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoryTest {


    private Story story;
    private UserProfile userProfile;
    @BeforeEach
    void setUp(){
        story = StoryFactory.mockStory();
        userProfile = UserProfileFactory.userProfile(1L);
    }

    @DisplayName("Story 좋아요는")
    @Nested
    class Story_Like{
        @DisplayName("아직 좋아요 한 상태가 아니면")
        @Nested
        class Context_Like{
            @DisplayName("좋아요 개수 증가")
            @Test
            void unLike(){
                // given && when
                story.like(userProfile);
                // then
                assertThat(story.getLikes().getCounts()).isEqualTo(1);
            }
        }
        
    }

    private Story createStoryOfId(Long id){
        return StoryFactory.mockStoryBy(UserProfileFactory.userProfile());
    }


}
