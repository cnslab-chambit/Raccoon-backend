package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.facade.user.UserFacadeService;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserSignUpController {
    private final UserFacadeService userFacadeService;
    @GetMapping("/login/oauth2/code/kakao")
    public ApiResponse<?> signUpWithKakaoLocal(@RequestParam String code){
        System.out.println(code+"코드입니다.");
        return ApiResponse.ok(userFacadeService.signupWithOauth(code,VendorType.KAKAO));
    }

    @GetMapping("/login/oauth2/schema/kakao")
    public void aaa(@RequestParam String code, HttpServletResponse response) throws IOException {
        response.sendRedirect("com.raccoon.sign://success?code="+code);
    }
}
