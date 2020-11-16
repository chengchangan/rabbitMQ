package com.cca.config;

import com.cca.constants.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author cca
 * @date 2018/4/21
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
     *  创建 direct 交换机，就是topic特殊情况，路由key精确匹配
     *
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


    /**
     * ============================
     */


    /**
     * 演练延迟消息
     *
     * 实现原理：
     *      设置消息的过期时间，然后其投递到一个普通队列（该队列无消费者，让其变成 [死信] ），
     *      等待过期时间一到，就会将其转发（普通队列会配置）到一个死信队列（死信队列：死了的信息，即将去往的队列）
     *      然后消费死信队列里的消息
     *
     * 说明：
     *      创建一个普通队列，不创建任何消费者，使其过期
     *      创建一个真正队列
     *
     *   流程：
     *      发送者 -> 普通队列 -> 死信队列 -> 被消费者监听
     */


    /**
     *  普通队列
     */
    @Bean
    public Queue directNormalQueue(){
        Map<String, Object> argument = new HashMap<>(2);
        // 消息到期后，即将转发的队列
        argument.put("x-dead-letter-exchange",Constants.DIRECT_EXCHANGE);
        argument.put("x-dead-letter-routing-key",Constants.DIRECT_DELAY_BINDING_KEY);
        return QueueBuilder
                .durable(Constants.DIRECT_NORMAL_QUEUE)
                .withArguments(argument)
                .build();
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue directDeadQueue(){
        return QueueBuilder.durable(Constants.DIRECT_DELAY_QUEUE).build();
    }



    /**
     * 绑定发送者和普通队列
     */
    @Bean
    public Binding bindingDirectNormalQueueToExchange(@Qualifier("directExchange") Exchange directExchange,
                                                @Qualifier("directNormalQueue") Queue directNormalQueue){
        return BindingBuilder.bind(directNormalQueue)
                .to(directExchange)
                .with(Constants.DIRECT_NORMAL_BINDING_KEY)
                .noargs();
    }

    /**
     * 绑定 普通队列转发信息的绑定
     */
    @Bean
    public Binding bindingDirectDeadQueueToExchange(@Qualifier("directExchange") Exchange directExchange,
                                                      @Qualifier("directDeadQueue") Queue directDeadQueue){
        return BindingBuilder.bind(directDeadQueue)
                .to(directExchange)
                .with(Constants.DIRECT_DELAY_BINDING_KEY)
                .noargs();
    }


}
