package kwu.raccoondomain.persistence.repository.utils;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
/*
    TODO-review P5
    정의된 타입으로 raw type이 강제되는 경우에는 SuppressWarnings 애너테이션을 활용해 보아도 좋습니다.
 */
public class OrderByNull extends OrderSpecifier {
    public static final OrderByNull DEFAULT = new OrderByNull();
    private OrderByNull(){
        super(Order.ASC, NullExpression.DEFAULT,NullHandling.Default);
    }
}
