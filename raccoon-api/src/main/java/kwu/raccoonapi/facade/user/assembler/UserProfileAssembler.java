package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.response.UserProfileGetResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import org.springframework.stereotype.Component;

@Component
public class UserProfileAssembler {
    public UserProfileUpdateResponse toUserProfileUpdateResponse(Long userId){
        return UserProfileUpdateResponse.of(userId);
    }

    public UserProfileGetResponse toUserProfileGetResponse(){
        return UserProfileGetResponse.of();
    }
}
