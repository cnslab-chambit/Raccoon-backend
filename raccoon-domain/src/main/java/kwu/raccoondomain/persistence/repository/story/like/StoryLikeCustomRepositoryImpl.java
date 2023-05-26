package kwu.raccoondomain.persistence.repository.story.like;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.like.Like;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.utils.OrderByNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static kwu.raccoondomain.persistence.domain.story.QStory.story;
import static kwu.raccoondomain.persistence.domain.story.like.QLike.like;


@RequiredArgsConstructor
public class StoryLikeCustomRepositoryImpl implements StoryLikeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //좋아요 상태를 반환하는 메서드
    public Optional<Like> storyLikeExist(UserProfile userProfile, Story story) {
        Like sLike= jpaQueryFactory
                .selectFrom(like)
                .where(like.userProfile.eq(userProfile),
                        like.story.eq(story))
                .fetchFirst();
        return Optional.ofNullable(sLike);
    }

    @Override
    public Map<Long, Long> countPerStory(List<Story> stories) {
        return jpaQueryFactory
                .from(like)
                .innerJoin(like.story,story)
                .where(story.in(stories))
                .groupBy(story.id)
                .orderBy(OrderByNull.DEFAULT)
                .transform(GroupBy.groupBy(story.id).as(like.count()));
    }
}