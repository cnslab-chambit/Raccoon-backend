package kwu.raccoondomain.unit.fixture.story;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.unit.fixture.user.userprofile.UserProfileFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StoryFactory {
    private StoryFactory(){
    }

    public static class Builder{

    }

    public static Story mockStoryBy(UserProfile userProfile){
        return Story.of(userProfile,"mock-url","mock story content");
    }

    public static Story mockStory(){
        return Story.of(null,"mock-url","mock story content");
    }

    public static List<Story> mockStoriesBy(List<UserProfile> userProfiles){
        AtomicInteger index = new AtomicInteger();
        return userProfiles.stream()
                .map(userProfile ->
                    Story.of(userProfile,"mock-url"+index.getAndIncrement(),
                            "mock story content")
                ).collect(Collectors.toList());
    }

}
