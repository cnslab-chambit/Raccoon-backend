package kwu.raccoonapi.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserSignUpResponse {
    private Long userId;
    private String accessToken;
    private boolean isAlreadySignedUp;
}
