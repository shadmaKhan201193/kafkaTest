package com.itl.utils;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



//@Configuration
public class MessagingConfig {



	public static final String QUEUEA = "queue.A";
	public static final String QUEUEB = "queue.B";
	public static final String ALLQUEUE = "queue.all";
	
	public static final String QUEUESECOND = "queue.second";
	
	public static final String ROUTING_A = "routing.A";
	public static final String ROUTING_B = "routing.B";
	public static final String ROUTING_B_resp = "routing.B.resp";
	
	public static final String ROUTINGALL = "routing.*";
	public static final String ROUTINGALLSECOND = "routing.#";
     public static final String FANOUtEXCHANGE = "exchange.fanout";
	  public static final String TOPICEXCHANGE = "exchange.topic";
	  public static final String DIRECTEXCHANGE = "exchange.direct";
	  public static final String HEADEREXCHANGE = "exchange.header";
	  
	  public static final String COLOUR = "colour";
	  public static final String ROUTING_CS_CREATE="custservice.create";
		public static final String ROUTING_CS_CREATE_RESP="custservice.createresp";
/*	@Bean
	public Queue queueA() {
		return new Queue(QUEUEA);
	}

	@Bean
	public Queue queueB() {
		return new Queue(QUEUEB);
	}
	
	@Bean
	public Queue allqueue() {
		return new Queue(ALLQUEUE);
	}
	
	@Bean
	public Queue secondqueue() {
		return new Queue(QUEUESECOND);
	}
	
////////////FanoutExchange  start/////
	@Bean
	public FanoutExchange exchangefanout() {
		return new FanoutExchange(FANOUtEXCHANGE);
	}
	
	@Bean
	public Binding bindingforfanoutA(Queue queueA, FanoutExchange exchange) {
		return BindingBuilder.bind(queueA)
				.to(exchange);
				
	}
	
	@Bean
	public Binding bindingforfanoutB(Queue queueB, FanoutExchange exchange) {
		return BindingBuilder.bind(queueB)
				.to(exchange);
				
	}
	
	
/////////////////////////////topic exchange start/////
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(TOPICEXCHANGE);
	}

	
	@Bean
	public Binding binding(Queue queueA, TopicExchange exchange) {
		return BindingBuilder.bind(queueA)
				.to(exchange)
		.with(ROUTING_A);
	}
	
	@Bean
	public Binding bindingB(Queue queueB, TopicExchange exchange) {
		return BindingBuilder.bind(queueB)
				.to(exchange)
				.with(ROUTING_B);
				
	}
	
	
	@Bean
	public Binding bindingForAll(Queue allqueue, TopicExchange exchangeTopic) {
		return BindingBuilder.bind(allqueue)
				.to(exchangeTopic)
				.with(ROUTINGALL);
	}
	
	
	@Bean
	public Binding bindingForSecond(Queue secondqueue, TopicExchange exchangeTopic) {
		return BindingBuilder.bind(secondqueue)
				.to(exchangeTopic)
				.with(ROUTINGALLSECOND);
	}
	//.................................
	@Bean
	public DirectExchange directexchange() {
		return new DirectExchange(DIRECTEXCHANGE);
	} 
	
	@Bean
	public Binding bindingForDirectExchA(Queue queueA, DirectExchange exchange) {
		return BindingBuilder.bind(queueA)
				.to(exchange)
		.with(ROUTING_A);
	}
	
	@Bean
	public Binding bindingForDirectExchB(Queue queueB, DirectExchange exchange) {
		return BindingBuilder.bind(queueB)
				.to(exchange)
				.with(ROUTING_B);
				
	}
	
	
	//.................................
	
		@Bean
		public HeadersExchange headerexchange() {
			return new HeadersExchange(HEADEREXCHANGE);
		} 
		
		@Bean
		public Binding bindingForHeadersExchA(Queue queueA, HeadersExchange exchange) {
			return BindingBuilder.bind(queueA)
					.to(exchange)
					.where(COLOUR)
					.matches("red");
			
		}
		
		@Bean
		public Binding bindingForHeadersExchB(Queue queueB, HeadersExchange exchange) {
			return BindingBuilder.bind(queueB)
					.to(exchange)
					.where(COLOUR)
					.matches("blue"); 
					
					
		}
	//..........................................
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate. setMessageConverter(converter());
		return rabbitTemplate;
	}

*/
}
