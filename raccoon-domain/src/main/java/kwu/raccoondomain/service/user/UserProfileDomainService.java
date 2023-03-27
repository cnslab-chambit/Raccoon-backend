package kwu.raccoondomain.service.user;

import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.query.user.UserProfileRepository;
import kwu.raccoondomain.persistence.query.user.UserRepository;
import kwu.raccooninfra.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.DoubleBuffer;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserProfileDomainService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final S3Service s3Service;
    private static final double EARTH_RADIUS=6371;

    public Long updateProfile(Long userId, UserProfileUpdateDto userProfileUpdateDto){
        String profileImgUrl = s3Service.upload(userProfileUpdateDto.getProfileImage());

        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));

        UserProfile userProfile = user.getUserProfile();

        userProfile.updateProfile(userProfileUpdateDto,profileImgUrl);
        return user.getId();
    }

    public UserProfile getProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile userProfile = user.getUserProfile();
        return userProfile;
    }
    public List<UserProfile> findAllProfile(){
        List<UserProfile> all = userProfileRepository.findAll();
        return all;
    }

    public Double getDistance(Long otherUserId, UserProfile userProfile){
        User other = userRepository.findById(otherUserId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile otherUserProfile = other.getUserProfile();
        Double d= null;

        try{
            Double lat1 =userProfile.getY();//위도(y)
            Double lon1= userProfile.getX();//경도(x)
            Double lat2= otherUserProfile.getY();
            Double lon2=otherUserProfile.getX();
            Double dLat = Math.toRadians(lat2 - lat1);
            Double dLon = Math.toRadians(lon2 - lon1);

            Double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            d =EARTH_RADIUS* c * 1000;    // Distance in m

        }catch (Exception e) {
            throw new RaccoonException(RetConsts.ERR603);
        }

        return d;
    }
}
