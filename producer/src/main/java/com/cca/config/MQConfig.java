package com.cca.config;

import message.Constants;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Administrator on 2018/4/21.
 */
@Configuration
public class MQConfig {


    /**
     * @return
     * 创建Topic交换机
     */
    @Bean
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(Constants.TOPIC_EXCHANGE).build();
    }

    @Bean
    public Queue topicQueue(){
        return QueueBuilder.durable(Constants.TOPIC_QUEUE).build();
    }

    @Bean
    public Binding topicBindingTest(@Qualifier("topicExchange") Exchange topicExchange,
        @Qualifier("topicQueue") Queue topicQueue){
        return BindingBuilder
            .bind(topicQueue)
            .to(topicExchange)
            .with(Constants.TOPIC_BINDING_KEY)
            .noargs();
    }


    /**
     * @return
     * 创建fanoutExchange交换机
     */
    @Bean
    public Exchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange(Constants.FANOUT_EXCHANGE).build();
    }

    @Bean
    public Queue fanoutQueueOne(){
        return QueueBuilder.durable(Constants.FANOUT_QUEUE_ONE).build();
    }

    @Bean
    public Queue fanoutQueueTwo(){
        return QueueBuilder.durable(Constants.FANOUT_QUEUE_TWO).build();
    }

    @Bean
    public Binding fanoutBingTestOne(@Qualifier("fanoutExchange") Exchange fanoutExchange,
        @Qualifier("fanoutQueueOne") Queue fanoutQueueOne){
        return BindingBuilder.bind(fanoutQueueOne)
            .to(fanoutExchange)
            .with(Constants.FANOUT_BINDING_KEY).noargs();
    }

    @Bean
    public Binding fanoutBingTestTwo(@Qualifier("fanoutExchange") Exchange fanoutExchange,
        @Qualifier("fanoutQueueTwo") Queue fanoutQueueTwo){
        return BindingBuilder.bind(fanoutQueueTwo)
            .to(fanoutExchange)
            .with(Constants.FANOUT_BINDING_KEY).noargs();
    }

}
