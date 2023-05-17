package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class MyProfileDetailsResponse {
    private String nickname;
    private List<String> profileImageUrls;
    private Gender gender;
    private Long age;
    private Long height;
    private String job;
    private Location location;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private AnimalType animalType;
    private Set<AnimalType> wantedAnimals;
    private String drink;
    private String edu;
    private Religion religion;

    public static MyProfileDetailsResponse of(UserProfile userProfile) {
        MyProfileDetailsResponse myProfileDetailsResponse = new MyProfileDetailsResponse();

        myProfileDetailsResponse.profileImageUrls=userProfile.getImages().
                stream().map(imageFile -> imageFile.getUrl()).collect(Collectors.toList());
        myProfileDetailsResponse.nickname=userProfile.getNickname();
        myProfileDetailsResponse.gender=userProfile.getGender();
        myProfileDetailsResponse.age=userProfile.getAge();
        myProfileDetailsResponse.height=userProfile.getHeight();
        myProfileDetailsResponse.job=userProfile.getJob();
        myProfileDetailsResponse.location=userProfile.getLocation();
        myProfileDetailsResponse.selfDescription=userProfile.getSelfDescription();
        myProfileDetailsResponse.smokingStatus=userProfile.getSmokingStatus();
        myProfileDetailsResponse.mbti=userProfile.getMbti();
        myProfileDetailsResponse.animalType =userProfile.getAnimalType();
        myProfileDetailsResponse.wantedAnimals =userProfile.getWantedAnimals();
        myProfileDetailsResponse.drink=userProfile.getDrink();
        myProfileDetailsResponse.edu =userProfile.getEdu();
        myProfileDetailsResponse.religion=userProfile.getReligion();

        return myProfileDetailsResponse;
    }
}
