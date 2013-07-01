package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageReceivingOperations {

	<P> Message<P> receive() throws MessagingException;

	<P> Message<P> receive(String destinationName) throws MessagingException;

	Object receiveAndConvert() throws MessagingException;

	Object receiveAndConvert(String destinationName) throws MessagingException;

	Message<?> sendAndReceive(Message<?> requestMessage);

	Message<?> sendAndReceive(String destinationName, Message<?> requestMessage);

	Object convertSendAndReceive(Object request);

	Object convertSendAndReceive(String destinationName, Object request);

	Object convertSendAndReceive(Object request, MessagePostProcessor requestPostProcessor);

	Object convertSendAndReceive(String destinationName, Object request, MessagePostProcessor requestPostProcessor);

}
