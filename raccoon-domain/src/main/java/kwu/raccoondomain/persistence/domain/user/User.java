package kwu.raccoondomain.persistence.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.dto.user.UserSignUpDto;
import kwu.raccoondomain.persistence.domain.user.enums.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "vendor_id")
    private String vendorId;

    @Column(name = "vendor_type")
    @Enumerated(value = EnumType.STRING)
    private VendorType vendorType;

    @Column(name = "user_email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    public static User fromSignUpDto(UserSignUpDto userSignUpDto){
        User user = new User();
        user.vendorId = userSignUpDto.getVendorId();
        user.vendorType = userSignUpDto.getVendorType();
        user.email = userSignUpDto.getEmail();

        UserProfile profile = new UserProfile(user);
        user.userProfile = profile;

        return user;
    }


}
