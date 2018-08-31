package com.cca;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
public class WebConfiguration {


  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory,
      SimpleRabbitListenerContainerFactoryConfigurer configurer,
      ThreadPoolTaskExecutor bizExecutor) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    ExecutorService service = Executors.newFixedThreadPool(100, bizExecutor);
    factory.setTaskExecutor(service);
//    factory.setConcurrentConsumers(50);
//    factory.setPrefetchCount(5);
    factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    factory.setMessageConverter(new Jackson2JsonMessageConverter());
    configurer.configure(factory, connectionFactory);
    return factory;
  }


  /**
   * 消费者线程池.
   */
  @Bean
  public ThreadPoolTaskExecutor bizExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(20);
    executor.setQueueCapacity(5);
    executor.setThreadNamePrefix("consumer-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setKeepAliveSeconds(3000);
    return executor;
  }

}
