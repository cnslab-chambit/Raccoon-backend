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
    private Integer dist; // 상대방 프로필 조회 시 거리 정보 추가
    private Set<AnimalType> wantedAnimals;
    private String drink;
    private String edu;
    private Religion religion;

    public static UserProfileDetailsResponse of(UserProfile userProfile, Integer dist) {
        UserProfileDetailsResponse userProfileDetailsResponse = new UserProfileDetailsResponse();

        userProfileDetailsResponse.profileImageUrls = userProfile.getImages()
                .stream()
                .map(imageFile -> imageFile.getUrl())
                .collect(Collectors.toList());
        userProfileDetailsResponse.nickname = userProfile.getNickname();
        userProfileDetailsResponse.gender = userProfile.getGender();
        userProfileDetailsResponse.age = userProfile.getAge();
        userProfileDetailsResponse.height = userProfile.getHeight();
        userProfileDetailsResponse.job = userProfile.getJob();
        userProfileDetailsResponse.location = userProfile.getLocation();
        userProfileDetailsResponse.selfDescription = userProfile.getSelfDescription();
        userProfileDetailsResponse.smokingStatus = userProfile.getSmokingStatus();
        userProfileDetailsResponse.mbti = userProfile.getMbti();
        userProfileDetailsResponse.animalType = userProfile.getAnimalType();
        userProfileDetailsResponse.dist = dist; // 거리 정보 추가
        userProfileDetailsResponse.wantedAnimals = userProfile.getWantedAnimals();
        userProfileDetailsResponse.drink = userProfile.getDrink();
        userProfileDetailsResponse.edu = userProfile.getEdu();
        userProfileDetailsResponse.religion = userProfile.getReligion();

        return userProfileDetailsResponse;
    }

    public static UserProfileDetailsResponse ofMy(UserProfile userProfile) {
        UserProfileDetailsResponse userProfileDetailsResponse = new UserProfileDetailsResponse();

        userProfileDetailsResponse.profileImageUrls = userProfile.getImages()
                .stream()
                .map(imageFile -> imageFile.getUrl())
                .collect(Collectors.toList());
        userProfileDetailsResponse.nickname = userProfile.getNickname();
        userProfileDetailsResponse.gender = userProfile.getGender();
        userProfileDetailsResponse.age = userProfile.getAge();
        userProfileDetailsResponse.height = userProfile.getHeight();
        userProfileDetailsResponse.job = userProfile.getJob();
        userProfileDetailsResponse.location = userProfile.getLocation();
        userProfileDetailsResponse.selfDescription = userProfile.getSelfDescription();
        userProfileDetailsResponse.smokingStatus = userProfile.getSmokingStatus();
        userProfileDetailsResponse.mbti = userProfile.getMbti();
        userProfileDetailsResponse.animalType = userProfile.getAnimalType();
        userProfileDetailsResponse.wantedAnimals = userProfile.getWantedAnimals();
        userProfileDetailsResponse.drink = userProfile.getDrink();
        userProfileDetailsResponse.edu = userProfile.getEdu();
        userProfileDetailsResponse.religion = userProfile.getReligion();

        return userProfileDetailsResponse;
    }
}
