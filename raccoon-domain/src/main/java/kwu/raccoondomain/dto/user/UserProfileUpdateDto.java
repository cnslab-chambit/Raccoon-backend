package kwu.raccoondomain.dto.user;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileUpdateDto {
    private List<MultipartFile> profileImages;
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private Animal animal;
    private Animal wantedAnimal;
    private String job;
    private Location location;
    private Double longitude;
    private Double latitude;
}
