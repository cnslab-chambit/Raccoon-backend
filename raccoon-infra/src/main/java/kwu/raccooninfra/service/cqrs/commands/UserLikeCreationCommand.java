package kwu.raccooninfra.service.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@ToString
@Getter
public class UserLikeCreationCommand {
    @TargetAggregateIdentifier
    private Long senderId;
    private Long receiverId;
}
