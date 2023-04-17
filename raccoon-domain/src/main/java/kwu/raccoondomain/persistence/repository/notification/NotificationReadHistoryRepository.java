package kwu.raccoondomain.persistence.repository.notification;

import kwu.raccoondomain.persistence.domain.notification.NotificationReadHistory;
import kwu.raccoondomain.persistence.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationReadHistoryRepository extends JpaRepository<NotificationReadHistory,Long> {
    Optional<NotificationReadHistory> findByUser(User user);
}
