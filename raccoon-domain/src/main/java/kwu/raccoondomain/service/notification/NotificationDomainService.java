package kwu.raccoondomain.service.notification;

import kwu.raccoondomain.persistence.domain.notification.NotificationReadHistory;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.repository.notification.NotificationQueryRepository;
import kwu.raccoondomain.persistence.repository.notification.NotificationReadHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationDomainService {

    private final NotificationQueryRepository notificationQueryRepository;
    private final NotificationReadHistoryRepository notificationReadHistoryRepository;

    public Optional<NotificationReadHistory> getUnreadNotificationHistory(User user){
        return notificationReadHistoryRepository.findByUser(user);
    }

    public Long getUnreadNotificationCount(User user,Long lastReadNotificationId){
        return notificationQueryRepository.getUnreadNotificationCount(user,lastReadNotificationId);
    }

}
