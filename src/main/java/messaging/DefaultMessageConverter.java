package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

public class DefaultMessageConverter implements MessageConverter {

	@Override
	public <T> Message<?> toMessage(T object) {
		System.out.println("converting " + object + " to message");
		return MessageBuilder.withPayload(object).build();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T fromMessage(Message<?> message) {
		System.out.println("converting " + message + " to object");
		return (T) message.getPayload();
	}

}
