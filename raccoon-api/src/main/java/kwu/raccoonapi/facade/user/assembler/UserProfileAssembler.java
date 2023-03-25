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
    public UserProfileDetailsResponse toUserProfileResponse(UserProfile profile){
        return UserProfileDetailsResponse.of(profile);
    }

    public UserProfileResponse toAllUserProfileResponse(UserProfile userProfile){
        return UserProfileResponse.of(
                userProfile.getId(),
                userProfile.getNickname(),
                userProfile.getGender(),
                userProfile.getAge(),
                userProfile.getHeight(),
                userProfile.getProfileImageUrl(),
                userProfile.getLocation(),
                userProfile.getAnimal()
        );
    }

}
