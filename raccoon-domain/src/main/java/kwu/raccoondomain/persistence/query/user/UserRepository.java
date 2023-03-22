package kwu.raccoondomain.persistence.query.user;

import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.enums.VendorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByVendorIdAndVendorType(String vendorId, VendorType vendorType);
}
