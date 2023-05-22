package kwu.raccooninfra.config;

import kwu.raccooncommon.consts.CommonConsts;
import org.axonframework.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.eventhandling.EventMessage;

public class CustomRoutingKeyResolver implements RoutingKeyResolver {
    @Override
    public String resolveRoutingKey(EventMessage<?> eventMessage) {
        String customRoutingKey = CommonConsts.USER_LIKE_ROUTING_KEY;

        return customRoutingKey;
    }
}
