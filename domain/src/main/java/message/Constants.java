package message;

import org.springframework.amqp.core.FanoutExchange;

/**
 * @author Shenzhen cca
 * @version 2018/8/31
 */
public class Constants {

  /**
   * factory
   */

  public static final String FACTORY = "rabbitListenerContainerFactory";


  /**
   *  topic 交换机
   */
  public static final String TOPIC_EXCHANGE="com.cca.topic";

  /**
   *  fanout 交换机
   */
  public static final String FANOUT_EXCHANGE="com.cca.fanout";




  /**
   *  topic 队列
   */
  public static final String TOPIC_BINDING_KEY="com.cca.test.print";
  public static final String TOPIC_QUEUE="com.cca.test.create";

  /**
   *  fanout 多队列
   */
  public static final String FANOUT_BINDING_KEY = "com.cca.test.fanout";
  public static final String FANOUT_QUEUE_ONE="com.cca.fanout.One";
  public static final String FANOUT_QUEUE_TWO="com.cca.fanout.Two";

}
