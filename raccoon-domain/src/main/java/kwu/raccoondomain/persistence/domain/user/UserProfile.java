package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

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

    @Column(name="user_job")
    private String job;

    @Column(name="user_location")
    @Enumerated(value = EnumType.STRING)
    private Location location;

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

    @Column(name="coordinate_x")
    private Double x;

    @Column(name="coordinate_y")
    private Double y;


    public void updateProfile(UserProfileUpdateDto userProfileUpdateDto,String profileImageUrl){
        if(userProfileUpdateDto.getNickname() != null) this.nickname = userProfileUpdateDto.getNickname();
        if(userProfileUpdateDto.getGender() != null) this.gender = userProfileUpdateDto.getGender();
        if(userProfileUpdateDto.getAge() != null) this.age = userProfileUpdateDto.getAge();
        if(userProfileUpdateDto.getHeight() != null) this.height = userProfileUpdateDto.getHeight();
        if(userProfileUpdateDto.getProfileImage() != null) this.profileImageUrl = profileImageUrl;
        if(userProfileUpdateDto.getSelfDescription() != null) this.selfDescription = userProfileUpdateDto.getSelfDescription();
        if(userProfileUpdateDto.getSmokingStatus() != null) this.smokingStatus = userProfileUpdateDto.getSmokingStatus();
        if(userProfileUpdateDto.getMbti() != null) this.mbti = userProfileUpdateDto.getMbti();
        if(userProfileUpdateDto.getAnimal() != null) this.animal = userProfileUpdateDto.getAnimal();
        if(userProfileUpdateDto.getWantedAnimal() != null) this.wantedAnimal = userProfileUpdateDto.getWantedAnimal();
        if(userProfileUpdateDto.getJob() != null ) this.job = userProfileUpdateDto.getJob();
        if(userProfileUpdateDto.getLocation() != null) this.location = userProfileUpdateDto.getLocation();
        if(userProfileUpdateDto.getX() != null) this.x=userProfileUpdateDto.getX();
        if(userProfileUpdateDto.getY()!=null)this.y=userProfileUpdateDto.getY();
    }
}
