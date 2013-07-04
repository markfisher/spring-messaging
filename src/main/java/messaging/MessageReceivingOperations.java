package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageReceivingOperations<D> extends MessageSendingOperations<D> {

	<P> Message<P> receive() throws MessagingException;

	<P> Message<P> receive(D destination) throws MessagingException;

	Object receiveAndConvert() throws MessagingException;

	Object receiveAndConvert(D destination) throws MessagingException;

	Message<?> sendAndReceive(Message<?> requestMessage);

	Message<?> sendAndReceive(D destination, Message<?> requestMessage);

	Object convertSendAndReceive(Object request);

	Object convertSendAndReceive(D destination, Object request);

	Object convertSendAndReceive(Object request, MessagePostProcessor requestPostProcessor);

	Object convertSendAndReceive(D destination, Object request, MessagePostProcessor requestPostProcessor);

}
