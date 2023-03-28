package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserCoordinateUpdateRequest;
import kwu.raccoonapi.dto.user.request.UserProfileUpdateRequest;
import kwu.raccoonapi.dto.user.response.UserProfileResponse;
import kwu.raccoonapi.dto.user.response.UserProfileDetailsResponse;
import kwu.raccoonapi.dto.user.response.UserProfileUpdateResponse;
import kwu.raccoonapi.facade.user.assembler.UserProfileAssembler;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
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

    @Transactional(readOnly = true)
    public UserProfileDetailsResponse getProfile(Long userId){
        UserProfile userProfile= userProfileDomainService.getProfile(userId);
        return userProfileAssembler.toUserProfileResponse(userProfile);
    }
    @Transactional(readOnly = true)
    public List<UserProfileResponse> getAllProfile(){
        List<UserProfile> allProfile = userProfileDomainService.findAllProfile();
        return allProfile.stream()
                .map(profile -> userProfileAssembler.toAllUserProfileResponse(profile))
                .collect(Collectors.toList());
    }

}
