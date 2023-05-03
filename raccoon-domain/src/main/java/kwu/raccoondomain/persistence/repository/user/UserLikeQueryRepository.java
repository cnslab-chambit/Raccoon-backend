package kwu.raccoondomain.persistence.repository.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.user.QUserLike;
import kwu.raccoondomain.persistence.domain.user.UserLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kwu.raccoondomain.persistence.domain.user.QUserLike.userLike;

@Repository
@RequiredArgsConstructor
public class UserLikeQueryRepository {
    private final JPAQueryFactory queryFactory;


    public UserLike findBySenderIdAndReceiverId(UserProfile sender, UserProfile receiver){
        BooleanBuilder where = new BooleanBuilder();

        where.and(userLike.isMatched.eq(false));
        where.and(userLike.sender.eq(receiver));
        where.and(userLike.receiver.eq(sender));

        return queryFactory.selectFrom(userLike)
                .where(where)
                .limit(1)
                .fetchOne();
    }

}
