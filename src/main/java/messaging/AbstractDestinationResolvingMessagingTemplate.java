package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessagePostProcessor;
import org.springframework.util.Assert;


public abstract class AbstractDestinationResolvingMessagingTemplate<D> extends AbstractMessagingTemplate<D>
		implements ResolvableDestinationMessageReceivingOperations<D> {

	private volatile DestinationResolver<D> destinationResolver;


	public void setDestinationResolver(DestinationResolver<D> destinationResolver) {
		this.destinationResolver = destinationResolver;
	}


	@Override
	public <P> void send(String destinationName, Message<P> message) {
		D destination = resolveDestination(destinationName);
		this.doSend(destination, message);
	}

	protected final D resolveDestination(String destinationName) {
		Assert.notNull(destinationResolver, "destinationResolver is required when passing a name only");
		return this.destinationResolver.resolveDestination(destinationName);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T message) {
		this.convertAndSend(destinationName, message, null);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T message, MessagePostProcessor postProcessor) {
		D destination = resolveDestination(destinationName);
		super.convertAndSend(destination, message, postProcessor);
	}

	@Override
	public <P> Message<P> receive(String destinationName) {
		D destination = resolveDestination(destinationName);
		return super.receive(destination);
	}

	@Override
	public Object receiveAndConvert(String destinationName) {
		D destination = resolveDestination(destinationName);
		return super.receiveAndConvert(destination);
	}

	@Override
	public Message<?> sendAndReceive(String destinationName, Message<?> requestMessage) {
		D destination = resolveDestination(destinationName);
		return super.sendAndReceive(destination, requestMessage);
	}

	@Override
	public Object convertSendAndReceive(String destinationName, Object request) {
		D destination = resolveDestination(destinationName);
		return super.convertSendAndReceive(destination, request);
	}

	@Override
	public Object convertSendAndReceive(String destinationName, Object request, MessagePostProcessor postProcessor) {
		D destination = resolveDestination(destinationName);
		return super.convertSendAndReceive(destination, request, postProcessor);
	}

}
