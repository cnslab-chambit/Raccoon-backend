package kwu.raccoonapi.controller.api.notification;

import kwu.raccoonapi.dto.ApiResponse;
import kwu.raccoonapi.facade.notification.NotificationFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationFacadeService notificationFacadeService;

    @GetMapping("/notifications/unread")
    public ApiResponse<Long> getUnreadNotifications(){
        return ApiResponse.ok(notificationFacadeService.getUnreadNotifications());
    }
}
