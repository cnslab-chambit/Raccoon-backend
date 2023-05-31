package kwu.raccoonapi.controller.api;

import kwu.raccoonapi.config.UserPrincipal;
import kwu.raccoonapi.utils.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    /*
        TODO-review P5
        단순 헬스 체크 용도라면 Spring Actuator를 활용해 보세요!
     */
    @GetMapping("/ping")
    public String check(){
        return "pong";
    }
    @GetMapping("/ping/user")
    public String userCheck(){
        return SecurityUtils.getUser().getId().toString();
    }
}