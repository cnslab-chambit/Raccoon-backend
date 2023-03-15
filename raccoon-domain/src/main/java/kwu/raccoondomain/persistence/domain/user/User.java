package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.dto.user.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "user_age")
    private Long age;

    @Column(name = "user_height")
    private Long height;

    //프로필 사진 -> 동물상 추출하는데 사용
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "self_description")
    private String selfDescription;

    @Column(name="smoking_or_not")
    private Boolean smokingStatus;

    @Column(name = "user_mbti")
    @Enumerated(value = EnumType.STRING)
    private Mbti mbti;

    @Column(name = "user_animal")
    @Enumerated(value = EnumType.STRING)
    private Animal animal;

    @Column(name = "wanted_animal")
    @Enumerated(value = EnumType.STRING)
    private Animal wantedAnimal;

    public static User fromSignUpDto(UserSignUpDto userSignUpDto){
        User user = new User();
        user.vendorId = userSignUpDto.getVendorId();
        user.vendorType = userSignUpDto.getVendorType();
        user.email = userSignUpDto.getEmail();
        return user;
    }

    public void initProfile(UserProfileUpdateDto userProfileUpdateDto,String profileImageUrl){
        this.nickname = userProfileUpdateDto.getNickname();
        this.gender = userProfileUpdateDto.getGender();
        this.age = userProfileUpdateDto.getAge();
        this.height = userProfileUpdateDto.getHeight();
        this.profileImageUrl = profileImageUrl;
        this.selfDescription = userProfileUpdateDto.getSelfDescription();
        this.smokingStatus = userProfileUpdateDto.getSmokingStatus();
        this.mbti = userProfileUpdateDto.getMbti();
        this.animal = userProfileUpdateDto.getAnimal();
        this.wantedAnimal = userProfileUpdateDto.getWantedAnimal();
    }

}
