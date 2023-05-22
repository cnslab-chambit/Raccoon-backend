package kwu.raccooninfra.service.cqrs.aggregate;

import kwu.raccooninfra.service.cqrs.commands.UserLikeCreationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import raccoon.raccoonevent.UserLikeCreationEvent;

@RequiredArgsConstructor
@Aggregate
@Slf4j
public class UserLikeAggregate {
    @AggregateIdentifier // 시간으로..?
    private Long senderId;
    private Long receiverId;

    @CommandHandler
    public UserLikeAggregate(UserLikeCreationCommand command){
        AggregateLifecycle.apply(new UserLikeCreationEvent(
                command.getSenderId(), command.getReceiverId()
        ));
    }

    @EventSourcingHandler
    protected void createUserLike(UserLikeCreationEvent event){
        this.senderId = event.getSenderId();
        this.senderId = event.getReceiverId();
    }

}
