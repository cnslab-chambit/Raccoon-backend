package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.plaf.synth.Region;
import java.util.Set;

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
    private AnimalType animalType;
    private Double longitude;
    private Double latitude;
    private Set<AnimalType> idealAnimalTypes;
    private String drink;
    private String edu;
    private Religion religion;

}
