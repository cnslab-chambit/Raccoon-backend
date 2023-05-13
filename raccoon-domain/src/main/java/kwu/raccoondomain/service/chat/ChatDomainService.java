package kwu.raccoondomain.service.chat;

import kwu.raccoondomain.dto.chat.ChatBriefDto;
import kwu.raccooninfra.service.chatting.ChattingInfraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatDomainService {
    private final ChattingInfraService chattingInfraService;

    public List<ChatBriefDto> findUserChatRooms(Long userId){
        return chattingInfraService.getRooms(userId).stream().map(chatRoomDto -> ChatBriefDto.of(
                chatRoomDto.getRoomId(), chatRoomDto.getOppositeUserId(),userId)).collect(Collectors.toList());
    }
}
