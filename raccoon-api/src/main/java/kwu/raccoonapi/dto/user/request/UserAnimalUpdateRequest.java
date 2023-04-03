package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import lombok.Getter;

@Getter
public class UserAnimalUpdateRequest {
    private AnimalType animalType;
}
