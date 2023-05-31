package kwu.raccoondomain.persistence.domain.files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/*
        TODO-review P5
        모든 엔티티가 hard-delete와 foreign key를 가져가는 거 같은데,
        soft-delete와 foreign key가 없는 것에 장단점을 고민해 보시면 좋을 거 같아요.
        foreign key, index에 대해 엔티티에 명시적으로 관리하는 것도 좋습니다.
    */
@Entity
@Table(name = "image_file")
@Getter
@NoArgsConstructor
public class ImageFile {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_file_id")
    private Long id;

    @Column(name = "image_file_url")
    private String url;

    @Column(name = "file_type")
    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    public void setUserProfile(UserProfile userProfile){
        if(this.userProfile != null){
            this.userProfile.getImages().remove(this);
        }
        this.userProfile = userProfile;
        userProfile.getImages().add(this);
    }

    public void setUrl(String url){
        this.url = url;
    }

}
