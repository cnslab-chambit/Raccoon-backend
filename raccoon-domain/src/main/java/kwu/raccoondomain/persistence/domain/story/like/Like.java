package kwu.raccoondomain.persistence.domain.story.like;

import com.fasterxml.jackson.databind.ObjectMapper;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name="story_like")
public class Like {
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

    public Like(Story story,UserProfile userProfile){
        this(null,story,userProfile);
    }
    public Like(Long id,Story story,UserProfile userProfile){
        this.id = id;
        this.story = story;
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Like like = (Like) o;
        return Objects.equals(story,like.getStory()) &&
                Objects.equals(userProfile,like.getUserProfile());
    }

    @Override
    public int hashCode(){ return Objects.hash(story,userProfile);}
}
