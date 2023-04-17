package kwu.raccoondomain.persistence.repository.user;

import kwu.raccoondomain.persistence.domain.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
