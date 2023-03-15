package kwu.raccoondomain.dto;

import com.sun.istack.NotNull;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.UserRole;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor(staticName = "of")
public class UserSignUpDto {
    private String vendorId;
    private VendorType vendorType;
    private String email;

/*    private UserRole role;
    private String location;
    private Gender gender;
    private String profileImageUrl;*/
}
