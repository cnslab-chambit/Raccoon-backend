package kwu.raccoonapi.controller.user;

import kwu.raccoonapi.RestDocsTest;
import kwu.raccoonapi.controller.api.user.UserProfileController;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.facade.user.UserProfileFacadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserProfileControllerTest extends RestDocsTest {

    @InjectMocks
    UserProfileController userProfileController;

    @Mock
    UserProfileFacadeService userProfileFacadeService;

    @BeforeEach
    void setup(RestDocumentationContextProvider provider){
        super.setup(userProfileController,provider);
    }

    @Test
    @DisplayName("마이페이지 프로필 조회")
    void 마이페이지_프로필_조회() throws Exception{
        when(userProfileFacadeService.getProfile(anyLong())).thenReturn(
                UserProfileDetailsResponse.of(
                        "오어엉",
                        "s3_url",

                )
        )
    }
}
