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
    public boolean checkDuplicateLike(UserProfile sender, UserProfile receiver){
        BooleanBuilder where = new BooleanBuilder();

        where.and(userLike.isMatched.eq(false));
        where.and(userLike.sender.eq(sender));
        where.and(userLike.receiver.eq(receiver));

        return queryFactory.selectFrom(userLike)
                .where(where)
                .limit(1)
                .fetchOne()!=null;
    }

    // 이미 sender 와 receiver 가 매칭된 상태인지 확인
    public boolean checkMatched(UserProfile sender, UserProfile receiver) {
        BooleanBuilder where = new BooleanBuilder();

        where.and(userLike.isMatched.eq(true));
        where.and(userLike.sender.eq(sender));
        where.and(userLike.receiver.eq(receiver));

        return queryFactory.selectFrom(userLike)
                .where(where)
                .limit(1)
                .fetchOne()!=null;
    }
}
