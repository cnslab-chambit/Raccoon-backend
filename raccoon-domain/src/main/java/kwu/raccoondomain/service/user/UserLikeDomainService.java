package kwu.raccoondomain.service.user;

import kwu.raccoondomain.aop.MatchingLikeAfterReturning;
import kwu.raccoondomain.persistence.domain.user.UserLike;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccoondomain.persistence.repository.user.UserLikeQueryRepository;
import kwu.raccoondomain.persistence.repository.user.UserLikeRepository;
import kwu.raccooninfra.service.rabbitmq.UserLikePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLikeDomainService {

    private final UserLikeQueryRepository userLikeQueryRepository;
    private final UserLikeRepository userLikeRepository;
    private final UserLikePublisher userLikePublisher;

    public UserLike sendLike(UserProfile sender,UserProfile receiver){
        return userLikeQueryRepository.findBySenderIdAndReceiverId(sender,receiver);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUserLike(UserProfile sender, UserProfile receiver){
        userLikeRepository.save(UserLike.of(sender,receiver));
    }

    @MatchingLikeAfterReturning
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void matchingLike(UserProfile sender, UserProfile receiver){
        UserLike s = userLikeQueryRepository.findBySenderIdAndReceiverId(sender, receiver);
        UserLike r = userLikeQueryRepository.findBySenderIdAndReceiverId(receiver, sender);
        s.match();
        r.match();
    }

}
