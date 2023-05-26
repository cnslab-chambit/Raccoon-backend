package kwu.raccoonapi.controller.api.chat;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.dto.chat.response.ChatMessageResponse;
import kwu.raccoonapi.dto.chat.response.ChatRoomBriefResponse;
import kwu.raccoonapi.facade.chat.ChatFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatFacadeService chatFacadeService;
    @GetMapping("/user/rooms")
    public ApiResponse<List<ChatRoomBriefResponse>> getUserChatRooms(){
        return ApiResponse.ok(chatFacadeService.getChatRooms());
    }
    @GetMapping("/chat/rooms/{roomId}")
    public ApiResponse<List<ChatMessageResponse>> getRoomMessages(@PathVariable Long roomId){
        return ApiResponse.ok(chatFacadeService.getChatMessages(roomId));
    }
}
