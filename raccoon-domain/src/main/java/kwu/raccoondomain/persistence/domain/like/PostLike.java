package kwu.raccoondomain.persistence.domain.like;

import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name="post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_like_id")
    private Long id;

    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="story_id")
    private Story story;

    private LocalDateTime likeTime;

    @Builder
    public PostLike(User user, Story story, LocalDateTime likeTime){
        this.user=user;
        this.story=story;
        this.likeTime=likeTime;
    }
}
