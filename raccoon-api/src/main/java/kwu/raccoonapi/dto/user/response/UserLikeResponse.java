package kwu.raccoonapi.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserLikeResponse {
    private boolean isMatched;
    private String reason;
}
