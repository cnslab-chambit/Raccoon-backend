package kwu.raccoonapi.facade.chat;

import kwu.raccoonapi.dto.chat.response.ChatMessageResponse;
import kwu.raccoonapi.dto.chat.response.ChatRoomBriefResponse;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.dto.chat.ChatBriefDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.service.chat.ChatDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatFacadeService {
    private final ChatDomainService chatDomainService;
    private final UserProfileDomainService userProfileDomainService;

    public List<ChatRoomBriefResponse> getChatRooms(){
        User sender = SecurityUtils.getUser();
        List<ChatBriefDto> rooms = chatDomainService.findUserChatRooms(sender.getId());
        Set<Long> oppositeUserIds = rooms.stream().map(ChatBriefDto::getOppositeUserId).collect(Collectors.toSet());
        List<UserProfile> profiles = userProfileDomainService.getProfiles(oppositeUserIds);

        return rooms.stream().map(room -> ChatRoomBriefResponse.of(room,
                        profiles.stream().filter(profile -> Objects.equals(profile.getId(), room.getOppositeUserId())).findAny().get()))
                .collect(Collectors.toList());
    }

    public List<ChatMessageResponse> getChatMessages(Long roomId){
        return chatDomainService.findChatMessages(roomId,SecurityUtils.getUser().getId()).stream().map(chatMessageDto -> ChatMessageResponse.of(
                chatMessageDto.getUserId(),chatMessageDto.getText(),chatMessageDto.getSeqId()
        )).collect(Collectors.toList());
    }
}
