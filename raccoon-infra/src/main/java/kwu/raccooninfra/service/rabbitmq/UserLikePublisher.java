package kwu.raccooninfra.service.rabbitmq;

import kwu.raccooncommon.consts.CommonConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLikePublisher {
    private final RabbitTemplate rabbitTemplate;
    public void pubUserLikeMessage(Long senderId, Long receiverId){
        rabbitTemplate.convertAndSend(CommonConsts.USER_LIKE_EXCHANGE,
                CommonConsts.USER_LIKE_ROUTING_KEY,new UserLikeMessage(senderId,receiverId),message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return message;
        });
    }
}
