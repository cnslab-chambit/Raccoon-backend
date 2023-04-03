package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "animal")
@Getter
@NoArgsConstructor
public class Animal {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_type")
    @Enumerated(value = EnumType.STRING)
    private AnimalType animalType;

    public void setAnimalType(AnimalType animalType){
        this.animalType = animalType;
    }
}
