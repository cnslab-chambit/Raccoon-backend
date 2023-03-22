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

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserProfileDomainService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final S3Service s3Service;
    public Long updateProfile(Long userId, UserProfileUpdateDto userProfileUpdateDto){
        String profileImgUrl = s3Service.upload(userProfileUpdateDto.getProfileImage());

        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));

        UserProfile userProfile = userProfileRepository
                .findUserProfileByUser(user).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));

        userProfile.updateProfile(userProfileUpdateDto,profileImgUrl);
        return user.getId();
    }

    public UserProfile getProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        UserProfile userProfile = userProfileRepository
                .findUserProfileByUser(user).orElseThrow(() -> new RaccoonException(RetConsts.ERR600));
        return userProfile;
    }
    public List<UserProfile> findAllProfile(){
        List<UserProfile> all = userProfileRepository.findAll();
        return all;
    }

}