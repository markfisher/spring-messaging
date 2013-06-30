package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;

public abstract class AbstractMessagingTemplate implements MessageSendingOperations {

	private volatile String defaultDestinationName;

	protected volatile MessageConverter converter = new DefaultMessageConverter();

	public void setDefaultDestinationName(String defaultDestinationName) {
		this.defaultDestinationName = defaultDestinationName;
	}

	public void setMessageConverter(MessageConverter converter) {
		this.converter = converter;
	}

	@Override
	public <P> void send(Message<P> message) throws MessagingException {
		this.send(this.defaultDestinationName, message);
	}

	@Override
	public <P> void send(String destinationName, Message<P> message) throws MessagingException {
		this.doSend(destinationName, message);
	}

	@Override
	public <T> void convertAndSend(T message) throws MessagingException {
		this.convertAndSend(this.defaultDestinationName, message);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T object) throws MessagingException {
		this.convertAndSend(destinationName, object, null);
	}

	@Override
	public <T> void convertAndSend(T object, MessagePostProcessor postProcessor) throws MessagingException {
		this.convertAndSend(this.defaultDestinationName, object, postProcessor);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T object, MessagePostProcessor postProcessor) throws MessagingException {
		Message<?> message = this.converter.toMessage(object);
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		this.send(destinationName, message);
	}

	protected abstract void doSend(String destinationName, Message<?> message);

}
