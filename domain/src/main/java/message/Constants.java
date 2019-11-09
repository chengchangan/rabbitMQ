package message;

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
   * topic 交换机
   */
  public static final String TOPIC_EXCHANGE = "com.cca.topic";

  /**
   * fanout 交换机
   */
  public static final String FANOUT_EXCHANGE = "com.cca.fanout";

  /**
   * direct 交换机
   */
  public static final String DIRECT_EXCHANGE = "com.cca.direct";


  /**
   * topic
   * routingKey 是模糊匹配
   * routingKey 根据routing 将消息从【交换机】路由到【队列】 , 再由监听器监听此队列进行消费
   */
  public static final String TOPIC_BINDING_KEY = "com.cca.topic.#";
  /**
   * 队列
   */
  public static final String TOPIC_QUEUE = "com.cca.topic.clear";

  /**
   * fanout 多队列
   * 没有routingKey
   *
   * fanout模式不需要routingKey，绑定到交换机的队列，交换机会给每个队列发送一条消息到每个队列
   */
  public static final String FANOUT_QUEUE_ONE = "com.cca.fanout.One";
  public static final String FANOUT_QUEUE_TWO = "com.cca.fanout.Two";

  /**
   * direct
   * routingKey 是精确的
   * routingKey 根据routing 将消息从【交换机】路由到【队列】 , 再由监听器监听此队列进行消费
   */
  public static final String DIRECT_BINDING_KEY = "com.cca.direct";

  /**
   * 队列
   */
  public static final String DIRECT_QUEUE = "com.cca.direct.test";
}
