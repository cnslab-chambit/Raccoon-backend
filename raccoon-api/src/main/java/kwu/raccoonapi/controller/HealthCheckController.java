package kwu.raccoonapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/ok/th")
    public String aa(){
        return "hi";
    }
}
