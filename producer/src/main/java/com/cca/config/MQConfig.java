package com.cca.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
     * 创建交换机
     */
    @Bean
    public TopicExchange testExchange(){
        return new TopicExchange("com.cca.test");
    }


    @Bean
    public Queue testQueue(){
        return new Queue("com.cca.test.create");
    }

    @Bean
    public Binding bindingTest(TopicExchange topicExchange,Queue testQueue){
        return BindingBuilder.bind(testQueue).to(topicExchange).with("com.cca.test.create");
    }

}
