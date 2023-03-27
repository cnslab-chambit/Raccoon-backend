package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileResponse {
    private Long profileId;
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String profileImageUrl;
    private Location location;
    private Animal animal;
    private double x;
    private double y;
}
