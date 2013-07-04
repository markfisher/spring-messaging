package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface ResolvableDestinationMessageReceivingOperations<D>
		extends MessageReceivingOperations<D>, ResolvableDestinationMessageSendingOperations<D> {

	<P> Message<P> receive(String destinationName) throws MessagingException;

	Object receiveAndConvert(String destinationName) throws MessagingException;

	Message<?> sendAndReceive(String destinationName, Message<?> requestMessage);

	Object convertSendAndReceive(String destinationName, Object request);

	Object convertSendAndReceive(String destinationName, Object request, MessagePostProcessor requestPostProcessor);

}
