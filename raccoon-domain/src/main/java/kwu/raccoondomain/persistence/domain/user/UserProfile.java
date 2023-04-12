package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.user.UserCoordinateUpdateDto;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.files.ImageFile;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Column(name = "user_religion")
    @Enumerated(value = EnumType.STRING)
    private Religion religion;

    @Column(name = "user_age")
    private Long age;

    @Column(name = "user_height")
    private Long height;

    @Column(name = "profile_images")
    @OneToMany(mappedBy = "userProfile",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageFile> images = new ArrayList<>();

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

    @Column(name = "user_edu")
    private String edu;
    @Column(name = "user_animal")
    @Enumerated(value = EnumType.STRING)
    private AnimalType animalType;

    @Column(name = "user_drink")
    private String drink;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AnimalType> wantedAnimals;

    @Column(name="user_longitude")
    private Double longitude;

    @Column(name="user_latitude")
    private Double latitude;


    public void updateProfile(UserProfileUpdateDto userProfileUpdateDto,List<String> imageUrls){
        if(userProfileUpdateDto.getNickname() != null) this.nickname = userProfileUpdateDto.getNickname();
        if(userProfileUpdateDto.getAge() != null) this.age = userProfileUpdateDto.getAge();
        if(userProfileUpdateDto.getHeight() != null) this.height = userProfileUpdateDto.getHeight();
        if(userProfileUpdateDto.getSelfDescription() != null) this.selfDescription = userProfileUpdateDto.getSelfDescription();
        if(userProfileUpdateDto.getSmokingStatus() != null) this.smokingStatus = userProfileUpdateDto.getSmokingStatus();
        if(userProfileUpdateDto.getMbti() != null) this.mbti = userProfileUpdateDto.getMbti();
        if(userProfileUpdateDto.getJob() != null ) this.job = userProfileUpdateDto.getJob();
        if(userProfileUpdateDto.getEdu() != null) this.edu = userProfileUpdateDto.getEdu();
        if(userProfileUpdateDto.getLocation() != null) this.location = userProfileUpdateDto.getLocation();
        if(userProfileUpdateDto.getDrink() != null) this.drink = userProfileUpdateDto.getDrink();
        if(userProfileUpdateDto.getReligion() != null) this.religion = userProfileUpdateDto.getReligion();
        if(userProfileUpdateDto.getWantedAnimalTypes() != null) updateWantedAnimal(userProfileUpdateDto.getWantedAnimalTypes());

        updateImages(imageUrls);
    }

    private void updateWantedAnimal(List<AnimalType> animalTypes){
        this.wantedAnimals.clear();
        for (AnimalType type : animalTypes) {
            this.wantedAnimals.add(type);
        }
    }

    private void updateImages(List<String> imageUrls){
        this.images.clear();
        for (String imageUrl : imageUrls) {
            ImageFile imageFile = new ImageFile();
            imageFile.setUserProfile(this);
            imageFile.setUrl(imageUrl);
            this.images.add(imageFile);
        }
    }

    public void updateCoordinate(UserCoordinateUpdateDto userCoordinateUpdateDto) {
        if(userCoordinateUpdateDto.getLongitude() != null) this.longitude =userCoordinateUpdateDto.getLongitude();
        if(userCoordinateUpdateDto.getLatitude()!=null) this.latitude =userCoordinateUpdateDto.getLatitude();
    }

    public void updateAnimal(AnimalType animalType) {
        this.animalType=animalType;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
