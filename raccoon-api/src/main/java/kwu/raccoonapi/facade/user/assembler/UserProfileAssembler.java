package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.response.UserProfileGetResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileAssembler {
    public UserProfileUpdateResponse toUserProfileUpdateResponse(Long userId){
        return UserProfileUpdateResponse.of(userId);
    }

    public UserProfileGetResponse toUserProfileResponse(UserProfile profile){
        return UserProfileGetResponse.of(profile);
    }
}
