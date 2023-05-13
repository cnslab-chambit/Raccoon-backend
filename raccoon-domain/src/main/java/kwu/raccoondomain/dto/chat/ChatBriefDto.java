package kwu.raccoondomain.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="of")
public class ChatBriefDto {
    private Long roomId;
    private Long oppositeUserId;
    private Long senderId;
}
