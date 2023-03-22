package kwu.raccoondomain.dto.story;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class StoryCreateDto {

    private UserProfile userProfile;

    private String contents;

    private MultipartFile storyImage;

    public static StoryCreateDto of(UserProfile userProfile,String contents,MultipartFile storyImage){
        return StoryCreateDto.builder()
                .userProfile(userProfile)
                .contents(contents)
                .storyImage(storyImage)
                .build();
    }

}
