package kwu.raccooninfra.dto;

import kwu.raccooncommon.dto.OauthResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
public class KakaoProfileResponse implements OauthResponse {
    private String id;
    private String email;
}
