package messaging;

import messaging.stomp.StompTemplate;

public class StompExample {

	public static void main(String[] args) {
		StompTemplate template = new StompTemplate();
		template.convertAndSend("/exchange/test", "foo");
	}

}
