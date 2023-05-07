package kwu.raccoondomain.unit.fixture.user.userprofile;

import kwu.raccoondomain.persistence.domain.files.ImageFile;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
public class UserProfileFixture {
    private Long id;
    private String nickname;
    private Gender gender;
    private Religion religion;
    private Long age;
    private Long height;
    private List<ImageFile> images;
    private String job;
    private Location location;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private String edu;
    private AnimalType animalType;
    private String drink;
    private Set<AnimalType> wantedAnimals;
    private Double longitude;
    private Double latitude;

    public UserProfile toUserProfile(){
        return new UserProfile(id,nickname,gender,religion,age,height,images,job,location,selfDescription,smokingStatus,mbti,
                edu,animalType,drink,wantedAnimals,longitude,latitude);
    }

}
