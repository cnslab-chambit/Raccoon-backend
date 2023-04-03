package kwu.raccooninfra.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kwu.raccooncommon.dto.OauthResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Data
@NoArgsConstructor
@Slf4j
public class KakaoProfileResponse implements OauthResponse {
    private String id;
    private String email;
    private String gender;
    private String birthDay;

    @JsonCreator
    public KakaoProfileResponse(
            @JsonProperty("id") String id,
            @JsonProperty("kakao_account") Map<String, String> data){
        this.id = id;
        this.email = data.get("email");
        this.gender = data.get("gender");
        this.birthDay = data.get("birthday");
    }
}
