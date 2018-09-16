package message;

/**
 * 值日生打扫卫生 ， 一个消息，多个当天值日生 打扫卫生
 *
 * @author Shenzhen cca
 * @version 2018/9/16
 */
public class ClearClassRoomOneMessage extends Message {

  private String clearRoomMessage;

  public String getClearRoomMessage() {
    return clearRoomMessage;
  }

  public void setClearRoomMessage(String clearRoomMessage) {
    this.clearRoomMessage = clearRoomMessage;
  }

  @Override
  public String exchange() {
    return Constants.TOPIC_EXCHANGE;
  }

  @Override
  public String routingKey() {
    return "com.cca.topic.one.clear";
  }
}
