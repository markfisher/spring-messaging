package messaging;

import org.springframework.integration.Message;
import org.springframework.integration.core.MessagePostProcessor;
import org.springframework.util.Assert;

public abstract class AbstractDestinationResolvingMessagingTemplate<D> extends AbstractMessagingTemplate
		implements ResolvableDestinationMessageReceivingOperations<D> {

	private volatile DestinationResolver<D> destinationResolver;


	public void setDestinationResolver(DestinationResolver<D> destinationResolver) {
		this.destinationResolver = destinationResolver;
	}


	@Override
	protected final void doSend(String destinationName, Message<?> message) {
		D destination = resolveDestination(destinationName);
		this.doSend(destination, message);
	}

	protected final D resolveDestination(String destinationName) {
		Assert.notNull(destinationResolver, "destinationResolver is required when passing a name only");
		return this.destinationResolver.resolveDestination(destinationName);
	}

	@Override
	protected <P> Message<P> doReceive(String destinationName) {
		D destination = resolveDestination(destinationName);
		return this.doReceive(destination);
	}

	@Override
	protected <S, R> Message<R> doSendAndReceive(String destinationName, Message<S> requestMessage) {
		D destination = resolveDestination(destinationName);
		return this.doSendAndReceive(destination, requestMessage);
	}


	@Override
	public <P> void send(D destination, Message<P> message) {
		this.doSend(destination, message);
	}

	protected abstract void doSend(D destination, Message<?> message);

	@Override
	public <T> void convertAndSend(D destination, T object) {
		this.convertAndSend(destination, object, null);
	}

	@Override
	public <T> void convertAndSend(D destination, T object, MessagePostProcessor postProcessor) {
		Message<?> message = this.converter.toMessage(object);
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		this.send(destination, message);
	}


	@Override
	public <P> Message<P> receive(D destination) {
		return this.doReceive(destination);
	}

	protected abstract <P> Message<P> doReceive(D destination);


	@Override
	public Object receiveAndConvert(D destination) {
		Message<?> message = this.doReceive(destination);
		return (message != null) ? this.converter.fromMessage(message) : null;
	}

	@Override
	public Message<?> sendAndReceive(D destination, Message<?> requestMessage) {
		return this.doSendAndReceive(destination, requestMessage);
	}

	protected abstract <S, R> Message<R> doSendAndReceive(D destination, Message<S> requestMessage);


	@Override
	public Object convertSendAndReceive(D destination, Object request) {
		Message<?> requestMessage = this.converter.toMessage(request);
		Message<?> replyMessage = this.sendAndReceive(requestMessage);
		return this.converter.fromMessage(replyMessage);
	}

	@Override
	public Object convertSendAndReceive(D destination, Object request, MessagePostProcessor postProcessor) {
		Message<?> requestMessage = this.converter.toMessage(request);
		requestMessage = postProcessor.postProcessMessage(requestMessage);
		Message<?> replyMessage = this.sendAndReceive(destination, requestMessage);
		return this.converter.fromMessage(replyMessage);
	}

}
