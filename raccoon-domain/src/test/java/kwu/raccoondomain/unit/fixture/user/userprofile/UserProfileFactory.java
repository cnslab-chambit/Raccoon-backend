package kwu.raccoondomain.unit.fixture.user.userprofile;

import kwu.raccoondomain.persistence.domain.user.UserProfile;

public class UserProfileFactory {

    private UserProfileFactory(){
    }

    public static UserProfile userProfile(){
        return createUserProfile(null);
    }
    public static UserProfile userProfile(Long id){
        return createUserProfile(id);
    }
    private static UserProfile createUserProfile(Long id){
        return UserProfileFixture.builder()
                .id(id)
                .build().toUserProfile();
    }
}
