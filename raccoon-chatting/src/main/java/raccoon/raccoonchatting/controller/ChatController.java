package raccoon.raccoonchatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raccoon.raccoonchatting.dto.ChatDto;
import raccoon.raccoonchatting.dto.ChatRoomBriefDto;
import raccoon.raccoonchatting.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/users/{userId}/rooms/{roomId}/chats")
    public List<ChatDto> getAll(@PathVariable Long userId, @PathVariable Long roomId){
        return chatService.findAll(userId,roomId);
    }

    @GetMapping("/users/{userId}/rooms")
    public List<ChatRoomBriefDto> getUserChatRoom(@PathVariable Long userId){
        return chatService.findUserRooms(userId);
    }
}
