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
     *
     *  支持模糊匹配routingKey , 将消息放到多个队列
     */
    @Bean
    public TopicExchange topicExchange(){
        return (TopicExchange) ExchangeBuilder.topicExchange(Constants.TOPIC_EXCHANGE).build();
    }

    @Bean
    public Queue topicQueue(){
        return QueueBuilder.durable(Constants.TOPIC_QUEUE).build();
    }


    @Bean
    public Binding topicOneBindingTest(@Qualifier("topicExchange") TopicExchange topicExchange,
        @Qualifier("topicQueue") Queue topicQueue){
        return BindingBuilder
            .bind(topicQueue)
            .to(topicExchange)
            .with(Constants.TOPIC_BINDING_KEY);
    }
    /**
     * =============================
     */


    /**
     * @return
     * 创建fanoutExchange交换机
     *
     *  fanout模式没有routingKey 直接将队列绑定到交换机
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return (FanoutExchange) ExchangeBuilder.fanoutExchange(Constants.FANOUT_EXCHANGE).build();
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
    public Binding fanoutBingTestOne(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
        @Qualifier("fanoutQueueOne") Queue fanoutQueueOne){
        return BindingBuilder.bind(fanoutQueueOne).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBingTestTwo(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
        @Qualifier("fanoutQueueTwo") Queue fanoutQueueTwo){
        return BindingBuilder.bind(fanoutQueueTwo).to(fanoutExchange);
    }

    /**
     * ========================
     */

    /**
     *
     * @return
     *
     *  创建 direct 交换机
     */
    @Bean
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange(Constants.DIRECT_EXCHANGE).build();
    }

    @Bean
    public Queue directQueue(){
        return QueueBuilder.durable(Constants.DIRECT_QUEUE).build();
    }

    @Bean
    public Binding bindingDirectQueueToExchange(@Qualifier("directExchange") Exchange directExchange,
        @Qualifier("directQueue") Queue directQueue){
        return BindingBuilder.bind(directQueue)
            .to(directExchange)
            .with(Constants.DIRECT_BINDING_KEY)
            .noargs();
    }

}
