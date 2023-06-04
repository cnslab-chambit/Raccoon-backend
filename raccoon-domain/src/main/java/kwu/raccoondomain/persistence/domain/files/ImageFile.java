package kwu.raccoondomain.persistence.domain.files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
