package messaging.stomp;

import messaging.AbstractMessagingTemplate;

import org.springframework.integration.Message;

public class StompTemplate extends AbstractMessagingTemplate {

	@Override
	protected void doSend(String destinationName, Message<?> message) {
		System.out.println("sending stomp message " + message + " to " + destinationName);
	}

	@Override
	protected <P> Message<P> doReceive(String destinationName) {
		// TODO: implement method or implement MessageSendingOperations
		return null;
	}

	@Override
	protected <S, R> Message<R> doSendAndReceive(String destinationName, Message<S> requestMessage) {
		// TODO: implement method or implement MessageSendingOperations
		return null;
	}

}
