package messaging.stomp;

import messaging.AbstractMessagingTemplate;

import org.springframework.integration.Message;

public class StompTemplate extends AbstractMessagingTemplate {

	@Override
	protected void doSend(String destinationName, Message<?> message) {
		System.out.println("sending stomp message " + message + " to " + destinationName);
	}

}
