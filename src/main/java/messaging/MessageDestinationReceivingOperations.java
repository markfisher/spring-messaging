package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;

public interface MessageDestinationReceivingOperations<D> extends MessageSendingOperations {

	<P> Message<P> receive(D destination) throws MessagingException;

	Object receiveAndConvert(D destination) throws MessagingException;

}
