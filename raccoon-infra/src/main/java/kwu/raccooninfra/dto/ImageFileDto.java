package kwu.raccooninfra.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ImageFileDto {
    private String title;
    private String url;
    private MultipartFile file;
}
