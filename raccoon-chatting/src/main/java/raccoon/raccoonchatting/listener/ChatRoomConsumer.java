package raccoon.raccoonchatting.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import raccoon.raccoonchatting.service.ChatService;

@Component
@RequiredArgsConstructor
public class ChatRoomConsumer {
    private final ChatService chatService;
    @RabbitListener(queues = "user-like")
    public void consume(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserLikeMessage userLikeMessage = objectMapper.readValue(new String(message.getBody()),
                UserLikeMessage.class);
        chatService.createChatRoom(userLikeMessage.getSenderId(),userLikeMessage.getReceiverId());
    }

}
