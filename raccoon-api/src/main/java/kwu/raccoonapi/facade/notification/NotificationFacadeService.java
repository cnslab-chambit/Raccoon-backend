package kwu.raccoonapi.facade.notification;

import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.notification.NotificationReadHistory;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.service.notification.NotificationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationFacadeService {

    private final NotificationDomainService notificationDomainService;

    public Long getUnreadNotifications(){
        User user = SecurityUtils.getUser();
        Optional<NotificationReadHistory> optNotificationHistory =
                notificationDomainService.getUnreadNotificationHistory(user);

        if(optNotificationHistory.isEmpty()){
            return notificationDomainService.getUnreadNotificationCount(user,0L);
        }
        NotificationReadHistory notificationReadHistory = optNotificationHistory.get();

        return notificationDomainService.getUnreadNotificationCount(user,notificationReadHistory.getLastReadNotificationId());
    }

}
