package com.itl;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itl.configuration.ApplicationConfigReader;
import com.itl.configuration.interceptors.LogInterceptor;

@EnableRabbit
//@ComponentScan(basePackages = { "com.itl" })
//@ComponentScan("com.itl")
@EnableEurekaClient
//@Configuration
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.itl", "com.products.aspect"})
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CSSpringBoot implements WebMvcConfigurer, RabbitListenerConfigurer{

	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private ApplicationConfigReader applicationConfig;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}
	
	public static void main(String[] args) {
		/* ApplicationContext ctx= */ SpringApplication.run(CSSpringBoot.class, args);
        
		//ctx.getBean("testClassForJaar", TestClassForJaar.class);
    }
        
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CSSpringBoot.class);
    }
    
    @Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
    	HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    	//clientHttpRequestFactory.setConnectTimeout(3000);
    	RestTemplate rt = new RestTemplate();
    	rt.setRequestFactory( clientHttpRequestFactory);
        return rt;
	}
    
    /*@Bean
	@LoadBalanced
	public WebClient getWebFlux() { 
    	//HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        return WebClient.create("http://localhost:8185");
        
	} */
	
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("setting LogInterceptor");
		registry.addInterceptor(new LogInterceptor(appName));
	}
    
    @Bean
	public ApplicationConfigReader applicationConfig() {
		return new ApplicationConfigReader();
	}
    
    @Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}
	
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(consumerJackson2MessageConverter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
	
	
	@Bean
	public RetryTemplate retryTemplate()
	{
		RetryTemplate retryTemplate = new RetryTemplate();

		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(20000);

		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(2);

		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		return retryTemplate;
	}
	
	
	
	
	
	
}



