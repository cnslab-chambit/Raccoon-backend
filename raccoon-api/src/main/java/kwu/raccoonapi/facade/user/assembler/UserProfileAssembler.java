package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.request.UserOnboardProfileRequest;
import kwu.raccoonapi.dto.user.response.UserOnboardProfileResponse;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserProfileAssembler {

    public UserProfileUpdateDto toUserOnboardProfileDto(UserOnboardProfileRequest request){
        return UserProfileUpdateDto.of(
                request.getProfileImage(),
                request.getNickname(),
                request.getGender(),
                request.getAge(),
                request.getHeight(),
                request.getSelfDescription(),
                request.getSmokingStatus(),
                request.getMbti(),
                request.getAnimal(),
                request.getWantedAnimal()
        );
    }

    public UserOnboardProfileResponse toUserOnboardProfileResponse(Long userId){
        return UserOnboardProfileResponse.of(userId);
    }

}
