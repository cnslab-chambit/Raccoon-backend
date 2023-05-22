package raccoon.raccoonevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class UserLikeCreationEvent {
    private Long senderId;
    private Long receiverId;
}
