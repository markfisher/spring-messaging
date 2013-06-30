package messaging;

import org.springframework.integration.Message;

public interface MessageConverter {

	<T> Message<?> toMessage(T object);

	<T> T fromMessage(Message<?> message);

}
