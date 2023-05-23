package kwu.raccoondomain.persistence.repository.user;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.raccoondomain.persistence.domain.user.QUserProfile;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kwu.raccoondomain.persistence.domain.user.QUserProfile.userProfile;

@Repository
@RequiredArgsConstructor
public class UserProfileCustomRepo {
    private final JPAQueryFactory queryFactory;
    

    public List<UserProfile> findRecommendation(UserProfile request){
        return queryFactory.selectFrom(userProfile)
                .where(userProfile.ne(request))
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
