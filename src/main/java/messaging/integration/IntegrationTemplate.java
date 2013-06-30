package messaging.integration;

import messaging.AbstractDestinationResolvingMessagingTemplate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;

public class IntegrationTemplate extends AbstractDestinationResolvingMessagingTemplate<MessageChannel> implements BeanFactoryAware {

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		super.setDestinationResolver(new BeanFactoryChannelResolver(beanFactory));
	}

	@Override
	protected void doSend(MessageChannel destination, Message<?> message) {
		destination.send(message);
	}

}
