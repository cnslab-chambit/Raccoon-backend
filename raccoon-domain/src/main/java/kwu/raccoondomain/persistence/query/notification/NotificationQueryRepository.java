package kwu.raccoondomain.persistence.query.notification;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kwu.raccoondomain.persistence.domain.notification.QNotification.notification;

@Repository
@RequiredArgsConstructor
public class NotificationQueryRepository {

    private final JPAQueryFactory queryFactory;


    public Long getUnreadNotificationCount(User user,Long lastReadNotificationId){
        BooleanBuilder where = new BooleanBuilder();

        where.and(notification.user.eq(user));
        where.and(notification.id.gt(lastReadNotificationId));

        return queryFactory.select(notification.count())
                .from(notification)
                .where(where)
                .fetchOne();
    }

}
