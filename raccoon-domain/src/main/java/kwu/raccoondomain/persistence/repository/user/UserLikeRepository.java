package kwu.raccoondomain.persistence.repository.user;

import kwu.raccoondomain.persistence.domain.user.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike,Long> {
}
