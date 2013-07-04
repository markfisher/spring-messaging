package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageSendingOperations<D> {

	<P> void send(Message<P> message) throws MessagingException;

	<P> void send(D destination, Message<P> message) throws MessagingException;

	<T> void convertAndSend(T message) throws MessagingException;

	<T> void convertAndSend(D destination, T message) throws MessagingException;

	<T> void convertAndSend(T message, MessagePostProcessor postProcessor) throws MessagingException;

	<T> void convertAndSend(D destination, T message, MessagePostProcessor postProcessor) throws MessagingException;

}
