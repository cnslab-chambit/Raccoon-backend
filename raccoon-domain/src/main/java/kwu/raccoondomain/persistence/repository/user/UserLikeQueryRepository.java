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
    /*
            TODO-review P2
            BooleanExpression 활용이 좋네요!
            하지만 npe 발생의 여지가 있을 거 같아요.
            조금 더 활용해서 재사용성이 가능한 조건들을 모아보면 어떨까요?
            https://sas-study.tistory.com/393
         */
    // 이미 sender 가 receiver 에게 좋아요를 보낸 적이 있는지 확인
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
