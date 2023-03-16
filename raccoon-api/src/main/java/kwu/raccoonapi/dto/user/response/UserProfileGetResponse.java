package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor()
public class UserProfileGetResponse {
    private String profileImageUrl;
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String job;
    private String location;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private Animal animal;
    private Animal wantedAnimal;

    //user를 parameter로 받아서 response로 바꿔서 반환
    public static UserProfileGetResponse of(UserProfile userProfile) {
        UserProfileGetResponse userProfileGetResponse = new UserProfileGetResponse();

        userProfileGetResponse.profileImageUrl=userProfile.getProfileImageUrl();
        userProfileGetResponse.nickname=userProfile.getNickname();
        userProfileGetResponse.gender=userProfile.getGender();
        userProfileGetResponse.age=userProfile.getAge();
        userProfileGetResponse.height=userProfile.getHeight();
        userProfileGetResponse.job=userProfile.getJob();
        userProfileGetResponse.location=userProfile.getLocation();
        userProfileGetResponse.selfDescription=userProfile.getSelfDescription();
        userProfileGetResponse.smokingStatus=userProfile.getSmokingStatus();
        userProfileGetResponse.mbti=userProfile.getMbti();
        userProfileGetResponse.animal=userProfile.getAnimal();
        userProfileGetResponse.wantedAnimal=userProfile.getWantedAnimal();

        return userProfileGetResponse;
    }
}
