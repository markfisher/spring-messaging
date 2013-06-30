package messaging;

import messaging.integration.IntegrationTemplate;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.PollableChannel;

public class IntegrationExample {

	public static void main(String[] args) {
		IntegrationTemplate template = new IntegrationTemplate();
		StaticApplicationContext context = new StaticApplicationContext();
		context.registerSingleton("testChannel", QueueChannel.class);
		template.setBeanFactory(context);
		template.convertAndSend("testChannel", "foo");
		System.out.println("received: " + context.getBean("testChannel", PollableChannel.class).receive());
	}

}
