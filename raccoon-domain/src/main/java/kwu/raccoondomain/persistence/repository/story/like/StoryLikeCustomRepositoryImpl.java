package kwu.raccoondomain.persistence.repository.story.like;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.story.QStory;
import kwu.raccoondomain.persistence.domain.story.QStoryLike;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.domain.story.StoryLike;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static kwu.raccoondomain.persistence.domain.story.QStory.story;
import static kwu.raccoondomain.persistence.domain.story.QStoryLike.storyLike;


@RequiredArgsConstructor
public class StoryLikeCustomRepositoryImpl implements StoryLikeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

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

    @Override
    public Map<Long, Long> countPerStory(List<Story> stories) {
        return jpaQueryFactory
                .from(storyLike)
                .innerJoin(storyLike.story,story)
                .where(story.in(stories))
                .groupBy(story.id)
                .transform(GroupBy.groupBy(story.id).as(storyLike.count()));
    }
}