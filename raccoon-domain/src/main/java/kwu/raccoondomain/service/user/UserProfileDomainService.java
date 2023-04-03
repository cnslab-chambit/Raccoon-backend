package kwu.raccoondomain.service.user;

import kwu.raccooncommon.consts.ret.RetConsts;
import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.dto.user.UserCoordinateUpdateDto;
import kwu.raccoondomain.dto.user.UserProfileUpdateDto;
import kwu.raccoondomain.persistence.domain.user.User;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.AnimalType;
import kwu.raccoondomain.persistence.query.user.UserProfileRepository;
import kwu.raccoondomain.persistence.query.user.UserRepository;
import kwu.raccooninfra.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserProfileDomainService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final S3Service s3Service;
    private static final double EARTH_RADIUS=6371;

    public Long updateProfile(Long userId, UserProfileUpdateDto userProfileUpdateDto){
        List<MultipartFile> profileImage = userProfileUpdateDto.getProfileImages();
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile multipartFile : profileImage) {
            imageUrls.add(s3Service.upload(multipartFile));
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile userProfile = user.getUserProfile();
        userProfile.updateProfile(userProfileUpdateDto,imageUrls);
        return user.getId();
    }

    public Long updateProfileCoordinate(Long userId, UserCoordinateUpdateDto userCoordinateUpdateDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile userProfile = user.getUserProfile();
        userProfile.updateCoordinate(userCoordinateUpdateDto);
        return user.getId();
    }
    public Long updateProfileAnimal(Long userId, AnimalType animalType) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile userProfile = user.getUserProfile();
        userProfile.updateAnimal(animalType);
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

    public Double getDistance(UserProfile otherUserProfile, UserProfile userProfile){
        Double distance= null;
        try{
            Double latitude1 =userProfile.getLatitude(); //위도(y)
            Double longitude1= userProfile.getLongitude(); //경도(x)
            Double latitude2= otherUserProfile.getLatitude();
            Double longitude2=otherUserProfile.getLongitude();
            Double dLatitude = Math.toRadians(latitude2 - latitude1);
            Double dLongitude = Math.toRadians(longitude2 - longitude1);

            Double a = Math.sin(dLatitude/2)* Math.sin(dLatitude/2)+ Math.cos(Math.toRadians(latitude1))* Math.cos(Math.toRadians(latitude2))* Math.sin(dLongitude/2)* Math.sin(dLongitude/2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            distance =EARTH_RADIUS* c * 1000;

        }catch (Exception e) {
            throw new RaccoonException(RetConsts.ERR603);
        }
        return distance;
    }
}
