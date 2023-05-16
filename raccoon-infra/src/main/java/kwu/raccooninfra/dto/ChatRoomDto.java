package kwu.raccooninfra.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatRoomDto {
    private Long roomId;
    private Long oppositeUserId;
    private String lastMessage;
    private LocalDateTime lastTime;
}
