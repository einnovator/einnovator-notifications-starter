package org.einnovator.notifications.client.amqp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.einnovator.notifications.client.config.NotificationsClientConfiguration;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import(RabbitAutoConfiguration.class)
@EnableConfigurationProperties({RabbitProperties.class})
@Profile(AmqpConfig.AMQP)
public class AmqpConfig {
	
	public static final String AMQP = "amqp";
	
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private NotificationsClientConfiguration config;


	@Bean
	public NotificationListenerContainer listenerContainer() {
		return new NotificationListenerContainer();
	}
	
	@Bean
	public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
	

	@Bean
	@ConditionalOnMissingBean(MessageConverter.class)
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.addQueueNames(config.getAmqp().getNotificationsQueue());
		container.setAutoDeclare(true);
		container.setAcknowledgeMode(AcknowledgeMode.AUTO);
		container.setConnectionFactory(connectionFactory);
		NotificationListenerContainer listenerContainer = listenerContainer();
		container.setMessageListener(listenerContainer);
		logger.info("listenerContainer:" + config.getAmqp().getNotificationsQueue() + " <- " + listenerContainer);		
		return container;
	}


	@Bean
	public RabbitAdmin admin(ConnectionFactory connectionFactory, List<Queue> queues, List<Binding> bindings) {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);
		try {
			for (Queue queue: queues) {
				logger.info("admin: declareQueue:" + queue);
				admin.declareQueue(queue);							
			}
			for (Binding binding: bindings) {
				logger.info("admin: declareBinding:" + binding);
				admin.declareBinding(binding);
			}

		} catch (AmqpConnectException e) {
			logger.error(e);
		}
		
		return admin;
	}
	

	
	@Bean
	public Queue notificationsQueue() {
		return new Queue(config.getAmqp().getNotificationsQueue());
	}
	
	@Bean
	public Exchange notificationsExchange() {
		return new FanoutExchange(config.getAmqp().getNotificationsExchange(), true, false);
	}
	@Bean
	public Binding notificationsBinding() {
		return new Binding(config.getAmqp().getNotificationsQueue(), DestinationType.QUEUE, config.getAmqp().getNotificationsExchange(), "" /* routingKey*/, null);
	}

}
