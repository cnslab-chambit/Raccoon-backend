package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.response.MyProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserProfileAssembler {
    public UserProfileUpdateResponse toUserProfileUpdateResponse(Long userId){
        return UserProfileUpdateResponse.of(userId);
    }
    public UserProfileDetailsResponse toUserProfileDetailsResponse(UserProfile profile,Double dist){
        return UserProfileDetailsResponse.of(profile,Integer.valueOf(dist.intValue()));
    }

    public MyProfileDetailsResponse toMyProfileResponse(UserProfile myProfile) {
        return MyProfileDetailsResponse.of(myProfile);
    }
}
