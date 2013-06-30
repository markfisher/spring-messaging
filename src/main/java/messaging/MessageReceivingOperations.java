package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;

public interface MessageReceivingOperations {

	<P> Message<P> receive() throws MessagingException;

	<P> Message<P> receive(String destinationName) throws MessagingException;

	Object receiveAndConvert() throws MessagingException;

	Object receiveAndConvert(String destinationName) throws MessagingException;

}
