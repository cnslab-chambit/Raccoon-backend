package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.facade.user.UserFacadeService;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserAuthController {
    private final UserFacadeService userFacadeService;
    @GetMapping("/login/oauth2/code/kakao")
    public ApiResponse<?> signUpWithKakaoLocal(@RequestParam String code){
        return ApiResponse.ok(userFacadeService.signupWithOauth(code,VendorType.KAKAO));
    }
    @GetMapping("/login/oauth2/schema/kakao")
    public void signUpClientSchema(@RequestParam String code, HttpServletResponse response) throws IOException {
        response.sendRedirect("com.raccoon.sign://success?code="+code);
    }
    @DeleteMapping("/user")
    public void signOut(){
        userFacadeService.deleteUser();
    }
}
