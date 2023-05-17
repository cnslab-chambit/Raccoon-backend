package kwu.raccoonapi.facade.user;

import kwu.raccoonapi.dto.user.request.UserLikeRequest;
import kwu.raccoonapi.dto.user.response.UserLikeResponse;
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
    public UserLikeResponse sendUserLike(UserLikeRequest userLikeRequest){
        UserProfile sender = userProfileDomainService.getProfile(SecurityUtils.getUser().getId());
        UserProfile receiver = userProfileDomainService.getProfile(userLikeRequest.getReceiverId());
        UserLike userLike = userLikeDomainService.sendLike(sender, receiver);
        // 내가 상대에게 중복된 좋아요를 누르면
        if (userLikeDomainService.duplicateLike(sender,receiver)){
            return UserLikeResponse.of(false,"중복된 좋아요");
        }

        // 이미 매칭된 상태에서 다시 좋아요를 누르면
        if (userLikeDomainService.isMatched(sender,receiver)){
            return UserLikeResponse.of(false,"매칭되었던 적이 있는 유저입니다.");
        }

        // 쌍방으로 좋아요를 표시한 적이 없으면 (내가 상대에게 첫 좋아요)
        if(userLike == null){
            userLikeDomainService.saveUserLike(sender,receiver);
            return UserLikeResponse.of(false,"상대방의 좋아요를 기다리세요");
        }

        // 상대는 나에게 좋아요를 보낸 상태라면
        userLikeDomainService.saveUserLike(sender,receiver);
        userLikeDomainService.matchingLike(sender,receiver);
        return UserLikeResponse.of(true,"채팅방이 생성되었어요");
    }

}
