package kwu.raccoondomain.persistence.query.story.like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.story.QStoryLike;
import kwu.raccoondomain.persistence.domain.story.StoryLike;

import javax.persistence.EntityManager;
import java.util.Optional;


public class StoryLikeCustomRepositoryImpl implements StoryLikeCustomRepository {
    JPAQueryFactory jpaQueryFactory;

    public StoryLikeCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    //좋아요 상태를 반환하는 메서드
    public Optional<StoryLike> storyLikeExist(Long userProfileId, Long storyId) {
        QStoryLike storyLike = QStoryLike.storyLike;
        StoryLike sLike= jpaQueryFactory
                .selectFrom(storyLike)
                .where(storyLike.userProfile.id.eq(userProfileId),
                        storyLike.story.id.eq(storyId))
                .fetchFirst();
        return Optional.ofNullable(sLike);
    }
}