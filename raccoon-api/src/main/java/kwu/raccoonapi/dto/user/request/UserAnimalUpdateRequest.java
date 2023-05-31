package kwu.raccoonapi.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class UserAnimalUpdateRequest {
    private AnimalType animalType;

    public void setAnimalType(@JsonProperty("animalType") String animalType){
        this.animalType = AnimalType.fromString(animalType);
    }
}
