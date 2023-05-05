package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserLikeRequest;
import kwu.raccoonapi.utils.SecurityUtils;
import kwu.raccoondomain.persistence.domain.user.UserLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.service.user.UserLikeDomainService;
import kwu.raccoondomain.service.user.UserProfileDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLikeFacadeService {
    private final UserLikeDomainService userLikeDomainService;
    private final UserProfileDomainService userProfileDomainService;

    @Transactional
    public void sendUserLike(UserLikeRequest userLikeRequest){
        UserProfile sender = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        UserProfile receiver = userProfileDomainService.getProfile(userLikeRequest.getReceiverId());
        UserLike userLike = userLikeDomainService.sendLike(sender, receiver);

        // 처음 보내는 거면
        if(userLike == null){
            userLikeDomainService.saveUserLike(sender,receiver);
            return;
        }
        // 상대가 이미 보냈다면
        userLikeDomainService.saveUserLike(sender,receiver);
        userLikeDomainService.matchingLike(sender,receiver);
    }

}
