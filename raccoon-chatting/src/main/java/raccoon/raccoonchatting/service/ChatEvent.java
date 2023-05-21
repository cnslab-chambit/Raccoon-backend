package raccoon.raccoonchatting.service;

import lombok.Data;
import raccoon.raccoonchatting.dto.ChatMessage;

@Data
public class ChatEvent {
    private ChatMessage chatMessage;
    public static ChatEvent of(ChatMessage chatMessage){
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setChatMessage(chatMessage);
        return chatEvent;
    }
}
