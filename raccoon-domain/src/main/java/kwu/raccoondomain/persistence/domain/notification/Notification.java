package kwu.raccoondomain.persistence.domain.notification;

import kwu.raccoondomain.persistence.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private User user;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "body",nullable = false)
    private String body;

}
