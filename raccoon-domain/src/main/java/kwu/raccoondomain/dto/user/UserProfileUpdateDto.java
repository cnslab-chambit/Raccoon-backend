package kwu.raccoondomain.dto.user;

import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileUpdateDto {
    private List<MultipartFile> profileImages;
    private String nickname;
    private Long age;
    private Long height;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private List<AnimalType> wantedAnimalTypes;
    private String job;
    private String edu;
    private Location location;
    private String drink;
    private Religion religion;
}
