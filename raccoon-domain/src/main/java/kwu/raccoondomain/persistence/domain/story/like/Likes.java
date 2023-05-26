package kwu.raccoondomain.persistence.domain.story.like;

import kwu.raccoondomain.persistence.domain.user.UserProfile;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Likes {
    @OneToMany(mappedBy = "story",fetch = FetchType.LAZY,
    cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Like> likes;

    public Likes() {
        this(new ArrayList<>());
    }

    public Likes(List<Like> likes){this.likes = likes;}

    public int getCounts(){return likes.size();}

    public boolean contains(Like like){
        return likes.stream()
                .anyMatch(like::equals);
    }

    public void add(Like like){
        if(likes.contains(like)){
            throw new RuntimeException("");
        }
        likes.add(like);
    }

    public void remove(Like like){
        if(!likes.contains(like)){
            throw new RuntimeException("");
        }
        likes.remove(like);
    }

    public List<UserProfile> getLikeUserProfiles(){
        return likes.stream()
                .map(Like::getUserProfile)
                .collect(Collectors.toList());
    }

    public List<Like> getLikes(){return likes;}

}
