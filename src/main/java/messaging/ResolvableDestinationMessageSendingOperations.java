package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface ResolvableDestinationMessageSendingOperations<D> extends MessageSendingOperations {

	<P> void send(D destination, Message<P> message) throws MessagingException;

	<T> void convertAndSend(D destination, T message) throws MessagingException;

	<T> void convertAndSend(D destination, T message, MessagePostProcessor postProcessor) throws MessagingException;

}
