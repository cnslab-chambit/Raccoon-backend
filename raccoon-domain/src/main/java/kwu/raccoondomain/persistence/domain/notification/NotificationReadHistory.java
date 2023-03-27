package kwu.raccoondomain.persistence.domain.notification;

import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notification_read_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationReadHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_read_history_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;

    @Column(name = "last_read_notification_id")
    private Long lastReadNotificationId;
}
