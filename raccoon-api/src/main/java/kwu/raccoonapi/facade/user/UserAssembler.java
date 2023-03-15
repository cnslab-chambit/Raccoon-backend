package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.UserSignUpResponse;
import kwu.raccoondomain.dto.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {
    public UserSignUpResponse toUserSignUpResponse(User user, String accessToken, boolean isFirstSignUp){
        return UserSignUpResponse.of(user.getId(),accessToken,isFirstSignUp);
    }

    public UserSignUpDto toUserSignUpDto(String vendorId, VendorType vendorType, String email){
        return UserSignUpDto.of(vendorId,vendorType,email);
    }
}
