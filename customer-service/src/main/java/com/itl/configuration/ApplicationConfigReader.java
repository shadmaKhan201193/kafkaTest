package com.itl.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rabbitmq.properties")
public class ApplicationConfigReader {

	@Value("${cnpoc.exchange.name}")
	private String cnpocExchange;

	@Value("${custservice.create.q}")
	private String custserviceCreateQueue;

	@Value("${custservice.createresp.q}")
	private String custserviceCreateRespQueue;
	
	@Value("${custservice.create.key}")
	private String custserviceCreateRoutingKey;
	
	@Value("${custservice.createresp.key}")
	private String custserviceCreateRespRoutingKey;

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	 
	@Bean public AmqpTemplate template(ConnectionFactory connectionFactory) {
		  RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		  rabbitTemplate. setMessageConverter(converter()); return rabbitTemplate;
	}
	
	@Bean
	public Queue custserviceCreateQueue() {
		return new Queue(custserviceCreateQueue);
	}
	
	@Bean
	public Queue custserviceCreateRespQueue() {
		return new Queue(custserviceCreateRespQueue);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(cnpocExchange);
	}
	
	@Bean
	public Binding binding(Queue custserviceCreateQueue, TopicExchange exchange) {
		return BindingBuilder.bind(custserviceCreateQueue)
				.to(exchange)
		.with(custserviceCreateRoutingKey);
	}
	
	@Bean
	public Binding bindingB(Queue custserviceCreateRespQueue, TopicExchange exchange) {
		return BindingBuilder.bind(custserviceCreateRespQueue)
				.to(exchange)
				.with(custserviceCreateRespRoutingKey);
	}
}