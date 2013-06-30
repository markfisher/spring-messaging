package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;
import org.springframework.util.Assert;

public abstract class AbstractDestinationResolvingMessagingTemplate<D> extends AbstractMessagingTemplate {

	private volatile DestinationResolver<D> destinationResolver;

	public void setDestinationResolver(DestinationResolver<D> destinationResolver) {
		this.destinationResolver = destinationResolver;
	}

	public <P> void send(D destination, Message<P> message) throws MessagingException {
		this.doSend(destination, message);
	}

	public <T> void convertAndSend(D destination, T object) throws MessagingException {
		this.convertAndSend(destination, object, null);
	}

	public <T> void convertAndSend(D destination, T object, MessagePostProcessor postProcessor) throws MessagingException {
		Message<?> message = this.converter.toMessage(object);
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		this.send(destination, message);
	}

	@Override
	protected final void doSend(String destinationName, Message<?> message) {
		Assert.notNull(destinationResolver, "destinationResolver is required when passing a name only");
		D destination = this.destinationResolver.resolveDestination(destinationName);
		this.doSend(destination, message);
	}

	protected abstract void doSend(D destination, Message<?> message);

}
