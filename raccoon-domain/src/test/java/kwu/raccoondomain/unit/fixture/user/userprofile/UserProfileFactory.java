package kwu.raccoondomain.unit.fixture.user.userprofile;

import kwu.raccoondomain.persistence.domain.user.UserProfile;

public class UserProfileFactory {

    private UserProfileFactory(){
    }

    public static UserProfile userProfile(){
        return createUserProfile(null);
    }

    private static UserProfile createUserProfile(Long id){
        return UserProfileFixture.builder()
                .id(id)
                .build().toUserProfile();
    }
}
