package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoondomain.dto.user.CompareUserDto;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.utils.SecurityUtils;
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

    public UserProfileDetailsResponse toMyProfileResponse(UserProfile myProfile) {
        return UserProfileDetailsResponse.ofMy(myProfile);
    }

    public CompareUserDto toCompareUserDto(Long oppositeUserId){
        return CompareUserDto.of(SecurityUtils.getUser().getId(),oppositeUserId);
    }

}
