package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageDestinationReceivingOperations<D>
		extends MessageReceivingOperations, MessageDestinationSendingOperations<D> {

	<P> Message<P> receive(D destination) throws MessagingException;

	Object receiveAndConvert(D destination) throws MessagingException;

	Message<?> sendAndReceive(D destination, Message<?> requestMessage);

	Object convertSendAndReceive(D destination, Object request);

	Object convertSendAndReceive(D destination, Object request, MessagePostProcessor requestPostProcessor);

}
