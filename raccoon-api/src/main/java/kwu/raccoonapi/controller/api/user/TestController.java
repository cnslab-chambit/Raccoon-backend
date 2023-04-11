package kwu.raccoonapi.controller.api.user;

import kwu.raccoondomain.persistence.domain.user.enums.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
    @PostMapping("/enum")
    public void enumTest(@RequestBody Location state){
        System.out.println(state.getState()+"ㅁㅁㅁ");
        return ;
    }

}
