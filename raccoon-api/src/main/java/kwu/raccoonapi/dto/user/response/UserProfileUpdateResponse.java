package kwu.raccoonapi.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserProfileUpdateResponse {
    private Long userId;
}
