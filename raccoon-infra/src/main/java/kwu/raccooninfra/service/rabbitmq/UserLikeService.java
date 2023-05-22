//package kwu.raccooninfra.service.rabbitmq;
//
//import kwu.raccooninfra.service.cqrs.commands.UserLikeCreationCommand;
//import lombok.RequiredArgsConstructor;
//import org.axonframework.commandhandling.gateway.CommandGateway;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//@RequiredArgsConstructor
//public class UserLikeService {
//    private final CommandGateway commandGateway;
//
//    public CompletableFuture<String> createUserLike(Long senderId, Long receiverId){
//        System.out.println("마마");
//        return commandGateway.send(new UserLikeCreationCommand(
//                senderId, receiverId));
//    }
//
//}
