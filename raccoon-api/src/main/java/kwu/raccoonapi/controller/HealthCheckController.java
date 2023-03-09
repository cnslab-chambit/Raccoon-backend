package kwu.raccoonapi.controller;

import kwu.raccoondomain.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {
    private final TestService testService;
    @GetMapping("/ok/th")
    public String aa(){
        return testService.aa();
    }
}
