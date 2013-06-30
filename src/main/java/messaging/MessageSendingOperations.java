package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageSendingOperations {

	<P> void send(Message<P> message) throws MessagingException;

	<P> void send(String destinationName, Message<P> message) throws MessagingException;

	<T> void convertAndSend(T message) throws MessagingException;

	<T> void convertAndSend(String destinationName, T message) throws MessagingException;

	<T> void convertAndSend(T message, MessagePostProcessor postProcessor) throws MessagingException;

	<T> void convertAndSend(String destinationName, T message, MessagePostProcessor postProcessor) throws MessagingException;

}
