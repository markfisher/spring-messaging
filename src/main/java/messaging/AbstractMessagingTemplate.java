package messaging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessagePostProcessor;
import org.springframework.util.Assert;

public abstract class AbstractMessagingTemplate implements MessageReceivingOperations {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private volatile String defaultDestinationName;

	protected volatile MessageConverter converter = new DefaultMessageConverter();


	public void setDefaultDestinationName(String defaultDestinationName) {
		this.defaultDestinationName = defaultDestinationName;
	}

	/**
	 * Set the {@link MessageConverter} that is to be used to convert
	 * between Messages and objects for this template.
	 * <p>The default is {@link DefaultMessageConverter}.
	 */
	public void setMessageConverter(MessageConverter messageConverter) {
		Assert.notNull(messageConverter, "'messageConverter' must not be null");
		this.converter = messageConverter;
	}


	@Override
	public <P> void send(Message<P> message) {
		this.send(getRequiredDefaultDestination(), message);
	}

	private String getRequiredDefaultDestination() {
		Assert.state(this.defaultDestinationName != null,
				"No 'defaultDestinationName' specified for MessagingTemplate. "
				+ "Unable to invoke method without an explicit destination argument.");
		return this.defaultDestinationName;
	}

	@Override
	public <P> void send(String destinationName, Message<P> message) {
		this.doSend(destinationName, message);
	}

	protected abstract void doSend(String destinationName, Message<?> message) ;


	@Override
	public <T> void convertAndSend(T message) {
		this.convertAndSend(getRequiredDefaultDestination(), message);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T object) {
		this.convertAndSend(destinationName, object, null);
	}

	@Override
	public <T> void convertAndSend(T object, MessagePostProcessor postProcessor) {
		this.convertAndSend(getRequiredDefaultDestination(), object, postProcessor);
	}

	@Override
	public <T> void convertAndSend(String destinationName, T object, MessagePostProcessor postProcessor)
			throws MessagingException {

		Message<?> message = this.converter.toMessage(object);
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		this.send(destinationName, message);
	}


	@Override
	public <P> Message<P> receive() {
		return this.receive(getRequiredDefaultDestination());
	}

	@Override
	public <P> Message<P> receive(String destinationName) {
		return this.doReceive(destinationName);
	}

	protected abstract <P> Message<P> doReceive(String destinationName);


	@Override
	public Object receiveAndConvert() {
		return this.receiveAndConvert(getRequiredDefaultDestination());
	}

	@Override
	public Object receiveAndConvert(String destinationName) {
		Message<Object> message = this.doReceive(destinationName);
		return (message != null) ? this.converter.fromMessage(message) : null;
	}


	@Override
	public Message<?> sendAndReceive(Message<?> requestMessage) {
		return this.sendAndReceive(getRequiredDefaultDestination(), requestMessage);
	}

	@Override
	public Message<?> sendAndReceive(String destinationName, Message<?> requestMessage) {
		return this.doSendAndReceive(destinationName, requestMessage);
	}

	protected abstract <S, R> Message<R> doSendAndReceive(String destinationName, Message<S> requestMessage);


	@Override
	public Object convertSendAndReceive(Object request) {
		return this.convertSendAndReceive(getRequiredDefaultDestination(), request);
	}

	@Override
	public Object convertSendAndReceive(String destinationName, Object request) {
		return this.convertSendAndReceive(destinationName, request, null);
	}

	@Override
	public Object convertSendAndReceive(Object request, MessagePostProcessor postProcessor) {
		return this.convertSendAndReceive(getRequiredDefaultDestination(), request, postProcessor);
	}

	@Override
	public Object convertSendAndReceive(String destinationName, Object request, MessagePostProcessor postProcessor) {
		Message<?> requestMessage = this.converter.toMessage(request);
		if (postProcessor != null) {
			requestMessage = postProcessor.postProcessMessage(requestMessage);
		}
		Message<?> replyMessage = this.sendAndReceive(destinationName, requestMessage);
		return this.converter.fromMessage(replyMessage);
	}

}
