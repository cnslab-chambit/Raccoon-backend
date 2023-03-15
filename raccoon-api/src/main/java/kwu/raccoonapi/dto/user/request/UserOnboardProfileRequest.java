package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserOnboardProfileRequest {
    private MultipartFile profileImage;
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private Animal animal;
    private Animal wantedAnimal;
}
