package kwu.raccoonapi.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class UserAnimalUpdateRequest {
    private AnimalType animalType;

    public void setAnimalType(@JsonProperty("animalType") String animalType){
        this.animalType = fromAnimal(animalType);
    }

    public static AnimalType fromAnimal(String animal){
        return Arrays.stream(AnimalType.values())
                .filter(t -> t.getKor().equals(animal))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(animal  + " is illegal argument."));
    }

}
