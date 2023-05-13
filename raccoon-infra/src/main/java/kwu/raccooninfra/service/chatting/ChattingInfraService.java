package kwu.raccooninfra.service.chatting;

import kwu.raccooninfra.apis.chatting.ChattingApi;
import kwu.raccooninfra.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChattingInfraService {
    private final ChattingApi chattingApi;

    public List<ChatRoomDto> getRooms(Long userId){
        return chattingApi.getUserChatRooms(userId);
    }

}
