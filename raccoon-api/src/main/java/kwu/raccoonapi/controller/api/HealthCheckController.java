package kwu.raccoonapi.controller.api;

import kwu.raccoonapi.config.UserPrincipal;
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
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser().getId().toString();
    }
}