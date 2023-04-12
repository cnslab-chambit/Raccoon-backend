package kwu.raccoondomain.persistence.domain.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "story")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id",nullable = false)
    private UserProfile userProfile;

    @Column(name = "contents",nullable = false,length = 500)
    private String contents;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "story_image")
    private String storyImageUrl;


    public static Story of(UserProfile userProfile, String storyImageUrl, StoryCreateDto dto){
        Story story = new Story();
        story.userProfile = userProfile;
        story.storyImageUrl = storyImageUrl;
        story.contents = dto.getContents();
        story.likeCount = 0L;
        return story;
    }
    public void updateStory(StoryUpdateDto storyUpdateDto, String storyImageUrl){
        if(storyUpdateDto.getContents() != null )this.contents=storyUpdateDto.getContents();
        if(storyUpdateDto.getStoryImage() != null )this.storyImageUrl=storyImageUrl;
    }

    public void increaseLikeCount(){
        this.likeCount++;
    }
    public void decreaseLikeCount(){
        this.likeCount--;
    }
}
