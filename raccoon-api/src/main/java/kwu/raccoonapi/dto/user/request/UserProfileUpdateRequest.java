package kwu.raccoonapi.dto.user.request;

import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserProfileUpdateRequest {
    //TODO validation
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
    private String job;
    private Location location;
    private Double longitude;
    private Double latitude;

    public UserProfileUpdateDto toUserProfileUpdateDto(){
        return UserProfileUpdateDto.of(
                profileImage,
                nickname,
                gender,
                age,
                height,
                selfDescription,
                smokingStatus,
                mbti,
                animal,
                wantedAnimal,
                job,
                location,
                longitude,
                latitude
        );
    }
}
