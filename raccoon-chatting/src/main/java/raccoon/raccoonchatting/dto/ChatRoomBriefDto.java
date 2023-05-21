package raccoon.raccoonchatting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatRoomBriefDto {
    private Long roomId;
    private Long oppositeUserId;
    private String lastMessage;

    private LocalDateTime lastTime;

    public static ChatRoomBriefDto of(ChatRoomQueryDto chatRoom,Long reqId){
        ChatRoomBriefDto chatRoomBriefDto = new ChatRoomBriefDto();
        chatRoomBriefDto.setRoomId(chatRoom.getId());
        if(chatRoom.getUser1Id()==reqId){
            chatRoomBriefDto.setOppositeUserId(chatRoom.getUser2Id());
        }else{
            chatRoomBriefDto.setOppositeUserId(chatRoom.getUser1Id());
        }
        chatRoomBriefDto.setLastMessage(chatRoom.getLastMessage());
        chatRoomBriefDto.setLastTime(chatRoom.getLastTime());

        return chatRoomBriefDto;
    }

}
