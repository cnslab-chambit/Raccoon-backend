package kwu.raccoonapi.dto.story.request;

import kwu.raccoonapi.controller.validation.LetterLength;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
/*
    TODO-review P1
    Data 애너테이션은 지양해야 합니다.
    https://velog.io/@bey1548/Data-%EC%A7%80%EC%96%91%EC%9D%B4%EC%9C%A0
    간단하게 정리되어 있는 문서를 참조하면 좋을 거 같아요.
 */
@Data
public class StoryCreateRequest {
    @LetterLength(min=10,max=200,nullable = false)
    private String contents;
    private MultipartFile storyImage;
}
