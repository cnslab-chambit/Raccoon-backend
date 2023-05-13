package kwu.raccoonapi.facade.chat.assembler;

import kwu.raccoonapi.dto.chat.response.ChatRoomBriefResponse;
import kwu.raccoondomain.dto.chat.ChatBriefDto;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatAssembler {

    public List<ChatRoomBriefResponse> toChatBriefResponses(List<ChatBriefDto> chatBriefDtoList,
                                                            List<UserProfile> userProfileList){

//        return Stream.concat(
//                chatBriefDtoList.stream().map(c -> ChatBriefResponse.of(c)),
//                userProfileList.stream().map(u -> ChatBriefResponse.of(u))
//                ).collect(Collectors.toList());
        return null;
    }
}
