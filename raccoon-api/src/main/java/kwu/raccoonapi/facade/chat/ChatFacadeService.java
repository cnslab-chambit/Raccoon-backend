package kwu.raccoonapi.facade.chat;

import kwu.raccoonapi.dto.chat.response.ChatMessageResponse;
import kwu.raccoonapi.dto.chat.response.ChatRoomBriefResponse;
import kwu.raccoonapi.facade.chat.assembler.ChatAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.dto.chat.ChatBriefDto;
import kwu.raccoondomain.service.chat.ChatDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatFacadeService {
    private final ChatDomainService chatDomainService;
    private final UserProfileDomainService userProfileDomainService;
    private final ChatAssembler chatAssembler;
    // #TODO 스트림으로 DB커넥션 ㅜㅈㄹ이기..
    public List<ChatRoomBriefResponse> getChatRooms(){

        List<ChatBriefDto> rooms = chatDomainService.findUserChatRooms(SecurityUtils.getUser().getId());

        return rooms.stream().map(a-> ChatRoomBriefResponse.of(a,
                userProfileDomainService.getProfile(a.getOppositeUserId()))).collect(Collectors.toList());
    }

    public List<ChatMessageResponse> getChatMessages(Long roomId){
        return chatDomainService.findChatMessages(roomId,SecurityUtils.getUser().getId()).stream().map(chatMessageDto -> ChatMessageResponse.of(
                chatMessageDto.getUserId(),chatMessageDto.getText(),chatMessageDto.getSeqId()
        )).collect(Collectors.toList());
    }
}
