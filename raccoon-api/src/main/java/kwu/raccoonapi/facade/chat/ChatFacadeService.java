package kwu.raccoonapi.facade.chat;

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

    // ##TODO DB 커넥션 줄이기 위해 userProfileDomain에서 userProfile list로 받고 stream
    public List<ChatRoomBriefResponse> getChatRooms(){

        List<ChatBriefDto> rooms = chatDomainService.findUserChatRooms(SecurityUtils.getUser().getId());

        return rooms.stream().map(a-> ChatRoomBriefResponse.of(a,
                userProfileDomainService.getProfile(a.getOppositeUserId()))).collect(Collectors.toList());
    }

}
