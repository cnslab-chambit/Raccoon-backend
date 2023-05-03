package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "user_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLike {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_profile_id",referencedColumnName = "user_profile_id")
    private UserProfile sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_profile_id",referencedColumnName = "user_profile_id")
    private UserProfile receiver;

    @Column(name = "match_flag",nullable = false)
    private Boolean isMatched;

    public static UserLike of(UserProfile sender,UserProfile receiver){
        UserLike userLike = new UserLike();
        userLike.sender = sender;
        userLike.receiver = receiver;
        userLike.isMatched = false;
        return userLike;
    }
    public void match(){
        isMatched = true;
    }
}
