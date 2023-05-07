package kwu.raccoondomain.unit.service;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.story.like.StoryLikeRepository;
import kwu.raccoondomain.service.story.StoryLikeDomainService;
import kwu.raccoondomain.unit.fixture.story.StoryFactory;
import kwu.raccoondomain.unit.fixture.user.userprofile.UserProfileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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

}
