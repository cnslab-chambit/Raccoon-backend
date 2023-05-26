package kwu.raccoondomain.persistence.repository.story;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.story.QStory;
import kwu.raccoondomain.persistence.domain.story.Story;
import kwu.raccoondomain.persistence.repository.utils.CursorPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static kwu.raccoondomain.persistence.domain.story.QStory.story;
import static kwu.raccoondomain.persistence.domain.user.QUserProfile.userProfile;

@Repository
@RequiredArgsConstructor
public class StoryQueryRepository {
    private final JPAQueryFactory queryFactory;
    public List<Story> paginate(CursorPageable<Long> cursorPageable){
        List<Sort.Order> orders = new ArrayList<>();
        BooleanBuilder builder = new BooleanBuilder();

        switch (cursorPageable.getSortBy()){
            case "latest":
                orders.add(Sort.Order.desc("id"));
                if(cursorPageable.getCursor()!=null){
                    builder.and(story.id.lt(cursorPageable.getCursor()));
                }
            case "likeCount":
                orders.add(Sort.Order.desc("likeCount"));
                orders.add(Sort.Order.asc("id"));
                if(cursorPageable.getLastCntValue()!=null&&cursorPageable.getCursor()!=null){
                    builder.and(QStory.story.likeCount.eq(cursorPageable.getLastCntValue()).and(
                            QStory.story.id.gt(cursorPageable.getLastCntValue())
                    ));
                    builder.or(QStory.story.likeCount.lt(cursorPageable.getLastCntValue()));
                }
            default:
        }

        Pageable pageable = PageRequest.of(cursorPageable.getCursor().intValue(),cursorPageable.getLimit().intValue(),Sort.by(orders));

        return queryFactory.select(story)
                .from(story)
                .innerJoin(story.userProfile,userProfile)
                .fetchJoin()
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    public List<Story> paginateMyStory(Pageable pageable, Long userId) {
        return queryFactory.select(story)
                .from(story)
                .innerJoin(story.userProfile,userProfile)
                .fetchJoin()
                .where(story.userProfile.id.eq(userId))
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort){
        List<OrderSpecifier> orders = new ArrayList<>();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Story.class,"story");
            orders.add(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });
        return orders;
    }
}
