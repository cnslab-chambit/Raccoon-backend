package kwu.raccoondomain.persistence.domain.story.like;

import kwu.raccoondomain.dto.story.StoryUpdateDto;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name="story_like")
public class StoryLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="story_like_id")
    private Long id;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="story_id")
    private Story story;

    private LocalDateTime likeTime;

    @Builder
    public StoryLike(UserProfile userProfile, Story story, LocalDateTime likeTime){
        this.userProfile=userProfile;
        this.story=story;
        this.likeTime=likeTime;
    }
}
