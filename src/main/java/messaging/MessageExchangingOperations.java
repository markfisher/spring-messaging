package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessagePostProcessor;

public interface MessageExchangingOperations {

	Message<?> sendAndReceive(Message<?> requestMessage);

	Message<?> sendAndReceive(String destinationName, Message<?> requestMessage);

	Object convertSendAndReceive(Object request);

	Object convertSendAndReceive(String destinationName, Object request);

	Object convertSendAndReceive(Object request, MessagePostProcessor requestPostProcessor);

	Object convertSendAndReceive(String destinationName, Object request, MessagePostProcessor requestPostProcessor);

}
