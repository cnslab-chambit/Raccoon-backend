package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.facade.user.UserFacadeService;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserSignUpController {
    private final UserFacadeService userFacadeService;
    @GetMapping("/login/oauth2/code/kakao")
    public ApiResponse<?> signUpWithKakao(@RequestParam String code){
//        System.out.println(code+"마마마마");
        userFacadeService.signupWithOauth(code, VendorType.KAKAO);
        return null;
    }
}
