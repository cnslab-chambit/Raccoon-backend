package kwu.raccooninfra.service.rabbitmq;

import kwu.raccooncommon.consts.CommonConsts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLikePublisher {
    private final RabbitTemplate rabbitTemplate;

//    @Retryable(value = AmqpException.class, maxAttempts = 3)
    public void pubUserLikeMessage(Long senderId, Long receiverId){
        rabbitTemplate.convertAndSend(CommonConsts.USER_LIKE_EXCHANGE,
                CommonConsts.USER_LIKE_ROUTING_KEY,new UserLikeMessage(senderId,receiverId),message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return message;
        });
    }
}
