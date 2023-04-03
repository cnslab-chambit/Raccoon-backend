package kwu.raccoondomain.dto.user;

import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserSignUpDto {
    private String vendorId;
    private VendorType vendorType;
    private String email;
    private Gender gender;
}
