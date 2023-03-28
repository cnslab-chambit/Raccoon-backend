package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import lombok.Getter;

@Getter
public class UserAnimalUpdateRequest {
    private Animal animal;
}
