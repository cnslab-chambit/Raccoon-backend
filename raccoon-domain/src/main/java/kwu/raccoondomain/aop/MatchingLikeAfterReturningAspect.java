package kwu.raccoondomain.aop;

import kwu.raccooncommon.exception.RaccoonException;
import kwu.raccoondomain.persistence.domain.user.UserProfile;
import kwu.raccooninfra.service.rabbitmq.UserLikePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class MatchingLikeAfterReturningAspect {
    private final UserLikePublisher userLikePublisher;
    @AfterReturning(pointcut = "@annotation(MatchingLikeAfterReturning)", returning = "result")
    public void pubAfterReturning(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        if (args.length != 2 || !(args[0] instanceof UserProfile) || !(args[1] instanceof UserProfile)) {
            log.error("Invalid arguments for matchingLike method, expected two UserProfile arguments");
            return;
        }
        UserProfile sender = (UserProfile) args[0];
        UserProfile receiver = (UserProfile) args[1];
        userLikePublisher.pubUserLikeMessage(sender.getId(), receiver.getId());
    }
}
