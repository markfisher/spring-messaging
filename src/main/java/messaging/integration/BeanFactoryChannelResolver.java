package messaging.integration;

import messaging.DestinationResolver;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.integration.MessageChannel;
import org.springframework.util.Assert;

public class BeanFactoryChannelResolver implements DestinationResolver<MessageChannel> {

	private final BeanFactory beanFactory;

	public BeanFactoryChannelResolver(BeanFactory beanFactory) {
		Assert.notNull(beanFactory, "beanFactory must not be null");
		this.beanFactory = beanFactory;
	}

	@Override
	public MessageChannel resolveDestination(String name) {
		return this.beanFactory.getBean(name, MessageChannel.class);
	}

}
