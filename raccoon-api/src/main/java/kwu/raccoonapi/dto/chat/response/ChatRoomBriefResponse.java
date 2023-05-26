package kwu.raccoonapi.dto.chat.response;

import kwu.raccoondomain.dto.chat.ChatBriefDto;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.Location;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoomBriefResponse {
    private ChatBriefDto chatBriefDto;
    private String oppositeNickname;
    private String oppositeProfileImageUrl;
    private Location oppositeLocation;
    public static ChatRoomBriefResponse of(ChatBriefDto dto, UserProfile userProfile){
        ChatRoomBriefResponse res = new ChatRoomBriefResponse();
        res.chatBriefDto = dto;
        res.oppositeNickname = userProfile.getNickname();
        res.oppositeProfileImageUrl = userProfile.getImages().get(0).getUrl();
        res.oppositeLocation = userProfile.getLocation();
        return res;
    }


}
