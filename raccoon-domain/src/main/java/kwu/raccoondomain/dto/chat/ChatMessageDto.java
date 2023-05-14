package kwu.raccoondomain.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="of")
public class ChatMessageDto {
    private Long userId;
    private Long roomId;
    private Long seqId;
    private String text;
}
