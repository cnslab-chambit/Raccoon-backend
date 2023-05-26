package kwu.raccoonapi.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserProfileUpdateRequest {
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
    public UserProfileUpdateDto toUserProfileUpdateDto(){
        return UserProfileUpdateDto.of(
                profileImages,
                nickname,
                age,
                height,
                selfDescription,
                smokingStatus,
                mbti,
                wantedAnimalTypes,
                job,
                edu,
                location,
                drink,
                religion
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

    public void setReligion(@JsonProperty("religion") String religion) {
        this.religion = fromReligion(religion);
    }

    public static Religion fromReligion(String s){
        return Arrays.stream(Religion.values())
                .filter(t -> t.getKor().equals(s))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(s  + " is illegal argument."));
    }

    public void setWantedAnimal(List<String> wantedAnimal) {
        this.wantedAnimalTypes = fromAnimal(wantedAnimal);
    }

    public static List<AnimalType> fromAnimal(List<String> list){
        return list.stream()
                .map(AnimalType::fromString)
                .collect(Collectors.toList());
    }
}
