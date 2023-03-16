package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.User;
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
    public static UserProfileGetResponse of(User user) {
        UserProfileGetResponse userProfileGetResponse = new UserProfileGetResponse();

//        userProfileGetResponse.profileImageUrl= user.getProfileImageUrl();
//        userProfileGetResponse.nickname= user.getNickname();
//        userProfileGetResponse.gender=user.getGender();
//        userProfileGetResponse.age=user.getAge();
//        userProfileGetResponse.height=user.getHeight();
//        userProfileGetResponse.job=user.getJob();
//        userProfileGetResponse.location=user.getLocation();
//        userProfileGetResponse.selfDescription=user.getSelfDescription();
//        userProfileGetResponse.smokingStatus=user.getSmokingStatus();
//        userProfileGetResponse.mbti=user.getMbti();
//        userProfileGetResponse.animal=user.getAnimal();
//        userProfileGetResponse.wantedAnimal=user.getWantedAnimal();

        return userProfileGetResponse;
    }
}
