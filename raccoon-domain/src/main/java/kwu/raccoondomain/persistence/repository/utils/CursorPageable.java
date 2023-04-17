package kwu.raccoondomain.persistence.repository.utils;

import com.querydsl.core.types.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CursorPageable<T> {
    private T cursor;
    private Long limit;
    private String sortBy;
    private String order;
    public static <T> CursorPageable<T> of(T cursor,Long limit,String sortBy,String order){
        return new CursorPageable<>(cursor,limit,sortBy,order);
    }
}
