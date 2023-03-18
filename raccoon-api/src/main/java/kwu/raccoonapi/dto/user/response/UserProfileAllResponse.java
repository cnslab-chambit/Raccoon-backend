package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileAllResponse {
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String profileImageUrl;
    private String location;
    private Animal animal;
}
