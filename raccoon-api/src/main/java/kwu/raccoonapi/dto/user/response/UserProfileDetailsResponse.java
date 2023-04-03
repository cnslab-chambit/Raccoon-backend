package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class UserProfileDetailsResponse {
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
    private AnimalType wantedAnimalType;
    private Double longitude;
    private Double latitude;

    //user를 parameter로 받아서 response로 바꿔서 반환
    public static UserProfileDetailsResponse of(UserProfile userProfile) {
        UserProfileDetailsResponse userProfileDetailsResponse = new UserProfileDetailsResponse();

        userProfileDetailsResponse.profileImageUrls=userProfile.getImages().
                stream().map(imageFile -> imageFile.getUrl()).collect(Collectors.toList());
        userProfileDetailsResponse.nickname=userProfile.getNickname();
        userProfileDetailsResponse.gender=userProfile.getGender();
        userProfileDetailsResponse.age=userProfile.getAge();
        userProfileDetailsResponse.height=userProfile.getHeight();
        userProfileDetailsResponse.job=userProfile.getJob();
        userProfileDetailsResponse.location=userProfile.getLocation();
        userProfileDetailsResponse.selfDescription=userProfile.getSelfDescription();
        userProfileDetailsResponse.smokingStatus=userProfile.getSmokingStatus();
        userProfileDetailsResponse.mbti=userProfile.getMbti();
        userProfileDetailsResponse.animalType =userProfile.getAnimalType();
        userProfileDetailsResponse.wantedAnimalType =userProfile.getWantedAnimal();
        userProfileDetailsResponse.longitude =userProfile.getLongitude();
        userProfileDetailsResponse.latitude =userProfile.getLatitude();

        return userProfileDetailsResponse;
    }
}
