package messaging.stomp;

import messaging.AbstractMessagingTemplate;

import org.springframework.integration.Message;

public class StompTemplate extends AbstractMessagingTemplate<String> {

	@Override
	protected final void doSend(String destinationName, Message<?> message) {
		System.out.println("sending stomp message " + message + " to " + destinationName);
	}

	@Override
	protected final <P> Message<P> doReceive(String destinationName) {
		// TODO: implement method or implement MessageSendingOperations
		return null;
	}

	@Override
	protected final <S, R> Message<R> doSendAndReceive(String destinationName, Message<S> requestMessage) {
		// TODO: implement method or implement MessageSendingOperations
		return null;
	}

}
