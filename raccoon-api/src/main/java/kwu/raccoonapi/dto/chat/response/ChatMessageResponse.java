package kwu.raccoonapi.dto.chat.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatMessageResponse {
    private Long sender;
    private String message;
    private Long seqId;
    public static ChatMessageResponse of(Long senderId,String message,Long seqId){
        ChatMessageResponse res = new ChatMessageResponse();
        res.sender = senderId;
        res.message = message;
        res.seqId = seqId;
        return res;
    }
}
