package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserGenderResponse {
    private Gender gender;
}
