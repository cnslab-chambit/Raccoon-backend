package kwu.raccoonapi.controller.api;

import kwu.raccoonapi.config.UserPrincipal;
import kwu.raccoonapi.utils.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/ping")
    public String check(){
        return "pong";
    }
    @GetMapping("/ping/user")
    public String userCheck(){
        return SecurityUtils.getUser().getId().toString();
    }
}