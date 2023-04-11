package kwu.raccoondomain.persistence.query.like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.like.PostLike;
import kwu.raccoondomain.persistence.domain.like.QPostLike;

import javax.persistence.EntityManager;
import java.util.Optional;

import static kwu.raccoondomain.persistence.domain.like.QPostLike.postLike;

public class PostLikeCustomRepositoryImpl implements PostLikeCustomRepository{
    JPAQueryFactory jpaQueryFactory;

    public PostLikeCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    //좋아요를 누른 적이 있는지 확인하는 메서드
    public Optional<PostLike> exist(Long userId, Long storyId) {
        PostLike pLike = jpaQueryFactory
                .selectFrom(postLike)
                .where(postLike.user.id.eq(userId),
                        postLike.story.id.eq(storyId))
                .fetchFirst();

        return Optional.ofNullable(pLike);
    }

    public long findPostLikeNum(Long storyId) {
        QPostLike postLike = QPostLike.postLike;
        return jpaQueryFactory
                .selectFrom(postLike)
                .where(postLike.story.id.eq(storyId))
                .fetchCount();
    }
}
