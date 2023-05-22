package kwu.raccoonapi;

import kwu.raccoondomain.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestService testService;
    @GetMapping("/aa")
    public void aa(){
        testService.aa();
    }
}
