package kwu.raccoonapi.controller.api.user;

import kwu.raccoonapi.facade.user.UserFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserDeleteController {
    private final UserFacadeService userFacadeService;

    @DeleteMapping(path="/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userFacadeService.deleteUser(userId);
    }
}
