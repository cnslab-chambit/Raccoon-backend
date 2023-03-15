package kwu.raccoonapi.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserOnboardProfileResponse {
    private Long userId;
}
