package kwu.raccoondomain.persistence.repository.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.user.QUserProfile;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.basic.BasicButtonUI;
import java.util.List;

import static kwu.raccoondomain.persistence.domain.user.QUserProfile.userProfile;

@Repository
@RequiredArgsConstructor
public class UserProfileCustomRepo {
    private final JPAQueryFactory queryFactory;
    

    public List<UserProfile> findRecommendation(UserProfile request){
        BooleanBuilder where = new BooleanBuilder();
        where.and(userProfile.ne(request));
        where.and(userProfile.animalType.in(request.getWantedAnimals()));
        where.and(userProfile.gender.ne(request.getGender()));
        return queryFactory.selectFrom(userProfile)
                .where(where)
                .orderBy(Expressions.stringTemplate("ST_Distance_Sphere({0},{1})",
                        Expressions.stringTemplate("POINT({0},{1})",
                                request.getLongitude(),
                                request.getLatitude()
                                ),
                        Expressions.stringTemplate("POINT({0},{1})",
                                userProfile.longitude,
                                userProfile.latitude
                        )
                        ).asc()
                ).fetch();
    }

}
