package kwu.raccoondomain.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName="of")
public class ChatBriefDto {
    private Long roomId;
    private Long oppositeUserId;
    private Long senderId;
    private String lastMessage;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastTime;
}
