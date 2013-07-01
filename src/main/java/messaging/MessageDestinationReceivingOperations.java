package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;

public interface MessageDestinationReceivingOperations<D> extends MessageReceivingOperations {

	<P> Message<P> receive(D destination) throws MessagingException;

	Object receiveAndConvert(D destination) throws MessagingException;

}
