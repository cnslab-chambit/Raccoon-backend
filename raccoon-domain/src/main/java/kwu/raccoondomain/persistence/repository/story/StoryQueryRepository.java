package kwu.raccoondomain.persistence.repository.story;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.story.QStory;
import kwu.raccoondomain.persistence.domain.story.Story;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.OrderedSetType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static kwu.raccoondomain.persistence.domain.story.QStory.story;

@Repository
@RequiredArgsConstructor
public class StoryQueryRepository {
    private final JPAQueryFactory queryFactory;
    public List<Story> paginate(Pageable pageable){
        return queryFactory.select(story)
                .from(story)
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
