package messaging.web;

import messaging.DefaultMessageConverter;
import messaging.MessageConverter;
import messaging.MessageSendingOperations;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagePostProcessor;
import org.springframework.integration.support.converter.SimpleMessageConverter;
import org.springframework.util.Assert;


public class WebMessagingTemplate implements MessageSendingOperations<String> {

	private final MessageChannel outputChannel;

	protected volatile MessageConverter converter = new DefaultMessageConverter();

	private volatile String defaultDestination;


	public WebMessagingTemplate(MessageChannel outputChannel) {
		Assert.notNull(this.outputChannel, "outputChannel is required");
		this.outputChannel = outputChannel;
	}


	/**
	 * Set the {@link MessageConverter} that is to be used to convert
	 * between Messages and objects for this template.
	 * <p>The default is {@link SimpleMessageConverter}.
	 */
	public void setMessageConverter(MessageConverter messageConverter) {
		Assert.notNull(messageConverter, "'messageConverter' must not be null");
		this.converter = messageConverter;
	}

	public void setDefaultDestination(String defaultDestination) {
		this.defaultDestination = defaultDestination;
	}


	@Override
	public <P> void send(Message<P> message) {
		this.send(getRequiredDefaultDestination(), message);
	}

	private String getRequiredDefaultDestination() {

		// TODO: maybe look up destination of current message (via ThreadLocal)

		Assert.state(this.defaultDestination != null,
				"No 'defaultDestination' specified for WebMessagingTemplate. " +
				"Unable to invoke method without an explicit destination argument.");

		return this.defaultDestination;
	}

	@Override
	public <P> void send(String destinationName, Message<P> message) {

	}

	protected <P> Message<P> addDestinationToMessage(Message<P> message, String destination) {
		Assert.notNull(destination, "destination is required");
//		WebMessageHeaderAccesssor headers = WebMessageHeaderAccesssor.wrap(message);
//		headers.setDestination(destination);
//		message = MessageBuilder.withPayload(message.getPayload()).copyHeaders(headers.toMap()).build();
		return message;
	}

	@Override
	public <T> void convertAndSend(T object) {
		this.convertAndSend(getRequiredDefaultDestination(), object);
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
	public <T> void convertAndSend(String destinationName, T object, MessagePostProcessor postProcessor) {
		Message<?> message = this.converter.toMessage(object);
		if (postProcessor != null) {
			message = postProcessor.postProcessMessage(message);
		}
		this.send(destinationName, message);
	}

}
