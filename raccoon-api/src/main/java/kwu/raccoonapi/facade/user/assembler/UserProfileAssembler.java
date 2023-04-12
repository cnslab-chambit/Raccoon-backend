package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.response.UserProfileResponse;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileAssembler {
    public UserProfileUpdateResponse toUserProfileUpdateResponse(Long userId){
        return UserProfileUpdateResponse.of(userId);
    }
    public UserProfileDetailsResponse toUserProfileDetailsResponse(UserProfile profile,Double dist){
        return UserProfileDetailsResponse.of(profile,Integer.valueOf(dist.intValue()));
    }

    public UserProfileResponse toAllUserProfileResponse(UserProfile userProfile){
        return UserProfileResponse.of(
                userProfile.getId(),
                userProfile.getNickname(),
                userProfile.getGender(),
                userProfile.getAge(),
                userProfile.getHeight(),
                userProfile.getImages().get(0).getUrl(),
                userProfile.getLocation(),
                userProfile.getAnimalType(),
                userProfile.getLongitude(),
                userProfile.getLatitude(),
                userProfile.getWantedAnimals(),
                userProfile.getDrink(),
                userProfile.getEdu(),
                userProfile.getReligion()
        );
    }

}
