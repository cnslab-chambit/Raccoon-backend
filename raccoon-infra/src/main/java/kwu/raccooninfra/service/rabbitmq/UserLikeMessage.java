package kwu.raccooninfra.service.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeMessage {
    private Long senderId;
    private Long receiverId;
}
