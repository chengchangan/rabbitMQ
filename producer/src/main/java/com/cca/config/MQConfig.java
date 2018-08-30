package com.cca.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
    public TopicExchange topicExchange(){
        return new TopicExchange("com.cca.test");
    }

    @Bean
    public Queue topicQueue(){
        return new Queue("com.cca.test.create");
    }

    @Bean
    public Binding bindingTest(TopicExchange topicExchange,Queue topicQueue){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("com.cca.test.create");
    }

    /**
     * @return
     * 创建fanoutExchange交换机
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("com.cca.fanoutExchange");
    }

    @Bean
    public Queue topicQueueOne(){
        return new Queue("com.cca.topicOne");
    }

    @Bean
    public Queue topicQueueTwo(){
        return new Queue("com.cca.topicTwo");
    }

//    public Binding bindingQueueToFanout(Queue topicQueueOne,Queue topicQueueTwo,FanoutExchange fanoutExchange){
//
//    }



}
