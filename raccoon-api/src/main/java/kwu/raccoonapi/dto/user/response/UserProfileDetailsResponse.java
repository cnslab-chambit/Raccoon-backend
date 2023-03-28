package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class UserProfileDetailsResponse {
    private String nickname;
    private String profileImageUrl;
    private Gender gender;
    private Long age;
    private Long height;
    private String job;
    private Location location;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private Animal animal;
    private Animal wantedAnimal;
    private Double x;
    private Double y;

    //user를 parameter로 받아서 response로 바꿔서 반환
    public static UserProfileDetailsResponse of(UserProfile userProfile) {
        UserProfileDetailsResponse userProfileDetailsResponse = new UserProfileDetailsResponse();

        userProfileDetailsResponse.profileImageUrl=userProfile.getProfileImageUrl();
        userProfileDetailsResponse.nickname=userProfile.getNickname();
        userProfileDetailsResponse.gender=userProfile.getGender();
        userProfileDetailsResponse.age=userProfile.getAge();
        userProfileDetailsResponse.height=userProfile.getHeight();
        userProfileDetailsResponse.job=userProfile.getJob();
        userProfileDetailsResponse.location=userProfile.getLocation();
        userProfileDetailsResponse.selfDescription=userProfile.getSelfDescription();
        userProfileDetailsResponse.smokingStatus=userProfile.getSmokingStatus();
        userProfileDetailsResponse.mbti=userProfile.getMbti();
        userProfileDetailsResponse.animal=userProfile.getAnimal();
        userProfileDetailsResponse.wantedAnimal=userProfile.getWantedAnimal();
        userProfileDetailsResponse.x=userProfile.getX();
        userProfileDetailsResponse.y=userProfile.getY();

        return userProfileDetailsResponse;
    }
}
