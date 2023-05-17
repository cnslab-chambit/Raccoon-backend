package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserAnimalUpdateRequest;
import kwu.raccoonapi.dto.user.request.UserCoordinateUpdateRequest;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.*;
import kwu.raccoonapi.facade.user.assembler.UserProfileAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.domain.user.enums.Gender;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserProfileFacadeService {
    private final UserProfileAssembler userProfileAssembler;
    private final UserProfileDomainService userProfileDomainService;

    @Transactional(readOnly = true)
    public MyProfileDetailsResponse getMyProfile() {
        UserProfile myProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        return userProfileAssembler.toMyProfileResponse(myProfile);
    }

    @Transactional
    public UserProfileUpdateResponse updateProfile(UserProfileUpdateRequest request){
        Long userId = userProfileDomainService
                .updateProfile(SecurityUtils.getUser().getId(), request.toUserProfileUpdateDto());
        return userProfileAssembler.toUserProfileUpdateResponse(userId);
    }

    @Transactional
    public UserProfileUpdateResponse updateProfileCoordinate(UserCoordinateUpdateRequest request) {
        Long userId = userProfileDomainService
                .updateProfileCoordinate(SecurityUtils.getUser().getId(), request.toUserCoordinateUpdateDto());
        return userProfileAssembler.toUserProfileUpdateResponse(userId);
    }

    @Transactional
    public UserProfileUpdateResponse updateProfileAnimal(UserAnimalUpdateRequest request) {
        Long userId = userProfileDomainService
                .updateProfileAnimal(SecurityUtils.getUser().getId(), request.getAnimalType());
        return userProfileAssembler.toUserProfileUpdateResponse(userId);
    }

    @Transactional(readOnly = true)
    public UserProfileDetailsResponse getProfile(Long userId){
        UserProfile userProfile= userProfileDomainService.getProfile(userId);
        UserProfile otherUserProfile = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        Double distance = userProfileDomainService.getDistance(otherUserProfile,userProfile);
        return userProfileAssembler.toUserProfileDetailsResponse(userProfile,distance);
    }

    @Transactional(readOnly = true)
    public UserGenderResponse getUserGender(){
        Gender gender=userProfileDomainService.getProfile(SecurityUtils.getUser().getId()).getGender();
        return UserGenderResponse.of(gender);
    }

}
