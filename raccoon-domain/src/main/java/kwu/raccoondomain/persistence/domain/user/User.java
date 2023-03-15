package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import kwu.raccoondomain.dto.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.UserRole;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "vendor_id")
    private String vendorId;

    @Column(name = "vendor_type")
    @Enumerated(value = EnumType.STRING)
    private VendorType vendorType;

    @Column(name = "use_email")
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "user_location")
    private String location;

    @Column(name = "use_age")
    private Long age;

    @Column(name = "user_height")
    private Long height;

    //프로필 사진 -> 동물상 추출하는데 사용
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "self_description")
    private String selfDescription;

    @Column(name="smoking_or_not")
    private boolean smokingStatus;

    @Column(name = "user_mbti")
    private String mbti;

    @Column(name = "what_animal")
    private String animal;


    public static User from(UserSignUpDto userSignUpDto){
        User user = new User();
        user.vendorId = userSignUpDto.getVendorId();
        user.vendorType = userSignUpDto.getVendorType();
        user.email = userSignUpDto.getEmail();

        return user;
    }

}
