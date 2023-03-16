package kwu.raccoonapi.dto.user.response;

import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileGetResponse {
    private String profileImageUrl;
    private String nickname;
    private Gender gender;
    private Long age;
    private Long height;
    private String job;
    private String location;
    private String selfDescription;
    private Boolean smokingStatus;
    private Mbti mbti;
    private Animal animal;
    private Animal wantedAnimal;

}
