package kwu.raccoonapi.facade.user.assembler;

import kwu.raccoonapi.dto.user.response.UserSignUpResponse;
import kwu.raccoondomain.dto.user.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {
    public UserSignUpResponse toUserSignUpResponse(User user, String accessToken, boolean isFirstSignUp){
        return UserSignUpResponse.of(user.getId(),accessToken,isFirstSignUp);
    }

    public UserSignUpDto toUserSignUpDto(String vendorId, VendorType vendorType, String email,String gender){
        return UserSignUpDto.of(vendorId,vendorType,email, Gender.from(gender));
    }
}
