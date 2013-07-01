package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageDestinationExchangingOperations<D> extends MessageExchangingOperations {

	Message<?> sendAndReceive(D destination, Message<?> requestMessage);

	Object convertSendAndReceive(D destination, Object request);

	Object convertSendAndReceive(D destination, Object request, MessagePostProcessor requestPostProcessor);

}
