package raccoon.raccoonchatting.dto;

import lombok.Data;
import raccoon.raccoonchatting.entity.Chat;

@Data
public class ChatDto {
    private Long userId;
    private Long roomId;
    private Long seqId;
    private String text;

    public static ChatDto of(Chat chat){
        ChatDto chatDto = new ChatDto();
        chatDto.roomId = chat.getRoomId();
        chatDto.seqId = chat.getSeqId();
        chatDto.userId = chat.getUserId();
        chatDto.text = chat.getText();
        return chatDto;
    }
}