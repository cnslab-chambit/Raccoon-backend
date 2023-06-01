package raccoon.raccoonchatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raccoon.raccoonchatting.dto.ChatDto;
import raccoon.raccoonchatting.dto.ChatMessage;
import raccoon.raccoonchatting.dto.ChatRoomBriefDto;
import raccoon.raccoonchatting.dto.ChatRoomQueryDto;
import raccoon.raccoonchatting.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatRepo chatRepo;
    private final ChatRoomRepository chatRoomRepository;

    public void createChatRoom(Long id1,Long id2){
        chatRoomRepository.save(ChatRoom.builder().user1Id(id1).user2Id(id2).build());
    }
    public void createChat(ChatMessage chatMessage){
        Long lastSeqId = chatRepo.getLastChatSeqId(chatMessage.getRoomId());

        Chat chat = Chat.of(
                lastSeqId == null ? 0L : lastSeqId + 1,
                chatMessage.getRoomId(),
                chatMessage.getSender(),
                chatMessage.getMessage()
        );
        chatRepository.save(chat);
    }

        public List<ChatDto> findAll(Long userId, Long roomId){
        List<Chat> chats = chatRepo.getAll(roomId);
        return chats.stream().map(ChatDto::of).collect(Collectors.toList());
    }

    public List<ChatRoomBriefDto> findUserRooms(Long userId){
        List<ChatRoomQueryDto> allUserChatRooms = chatRepo.findAllUserChatRooms(userId);
        System.out.println(allUserChatRooms.size()+"마마");
        for (ChatRoomQueryDto allUserChatRoom : allUserChatRooms) {
            System.out.println(allUserChatRoom.getLastMessage()+"마마");
        }
        List<ChatRoomBriefDto> ret = allUserChatRooms.stream().map(e->ChatRoomBriefDto.of(e,userId)).collect(Collectors.toList());
        return ret;
    }

}
