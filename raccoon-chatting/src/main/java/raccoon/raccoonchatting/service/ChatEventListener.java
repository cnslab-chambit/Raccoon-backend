package raccoon.raccoonchatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatEventListener {
    private final ChatService chatService;
    @EventListener
    public void listenChatEvent(final ChatEvent chatEvent){
        chatService.createChat(chatEvent.getChatMessage());
    }
}
