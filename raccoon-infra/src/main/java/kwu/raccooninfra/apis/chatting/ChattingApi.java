package kwu.raccooninfra.apis.chatting;

import kwu.raccooninfra.dto.ChatDto;
import kwu.raccooninfra.dto.ChatRoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "raccoon-chatting",url = "${raccoon.chatting}",path = "/chat")
public interface ChattingApi {
    @GetMapping("/users/{userId}/rooms")
    List<ChatRoomDto> getUserChatRooms(@PathVariable(value = "userId") Long userId);

    @GetMapping("/users/{userId}/rooms/{roomId}/chats")
    List<ChatDto> getUserChatMessages(@PathVariable(value = "roomId") Long roomId,
                                             @PathVariable(value = "userId") Long userId);
}
