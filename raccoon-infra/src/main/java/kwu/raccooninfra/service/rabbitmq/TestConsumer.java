//package kwu.raccooninfra.service.rabbitmq;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestConsumer {
//
//    @RabbitListener(queues = "user-like")
//    public void consume(Message message) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        UserLikeMessage userLikeMessage = objectMapper.readValue(new String(message.getBody()),
//                UserLikeMessage.class);
//        System.out.println(userLikeMessage+"마마");
//    }
//}
