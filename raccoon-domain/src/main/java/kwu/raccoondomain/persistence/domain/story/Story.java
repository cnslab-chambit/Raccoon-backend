package kwu.raccoondomain.persistence.domain.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.story.StoryCreateDto;
import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.story.like.Like;
import kwu.raccoondomain.persistence.domain.story.like.Likes;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Embedded
    private Likes likes;

    private Long likeCount;

    @Column(name = "story_image")
    private String storyImageUrl;


    public static Story of(UserProfile userProfile, String storyImageUrl, String contents){
        Story story = new Story();
        story.userProfile = userProfile;
        story.storyImageUrl = storyImageUrl;
        story.contents = contents;
        story.likes = new Likes();
        story.likeCount = 0L;
        return story;
    }
    public void updateStory(StoryUpdateDto storyUpdateDto, String storyImageUrl){
        if(storyUpdateDto.getContents() != null )this.contents=storyUpdateDto.getContents();
        if(storyUpdateDto.getStoryImage() != null )this.storyImageUrl=storyImageUrl;
    }

    public void like(UserProfile userProfile){
        Like like = new Like(this,userProfile);
        likes.add(like);
        likeCount += 1;
    }

    public void unlike(UserProfile userProfile){
        Like like = new Like(this,userProfile);
        likes.remove(like);
        likeCount -= 1;
    }

}
