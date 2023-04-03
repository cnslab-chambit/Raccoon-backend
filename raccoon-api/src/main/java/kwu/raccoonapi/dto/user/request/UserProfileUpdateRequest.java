package kwu.raccoonapi.dto.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.enums.Animal;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import kwu.raccoondomain.persistence.domain.user.enums.Mbti;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Data
public class UserProfileUpdateRequest {
    //TODO validation
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

    public UserProfileUpdateDto toUserProfileUpdateDto(){
        return UserProfileUpdateDto.of(
                profileImages,
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
    public void setState(@JsonProperty("state") String state){
        this.location = fromState(state);
    }
    public static Location fromState(String state) {
        return Arrays.stream(Location.values())
                .filter(t -> t.getState().equals(state))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(state  + " is illegal argument."));
    }


}
