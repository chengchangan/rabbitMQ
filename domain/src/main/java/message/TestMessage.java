package message;

/**
 * @author cca
 * @version 2019/4/30 16:17 
 */
public class TestMessage extends Message {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String exchange() {
    return "order.exchange";
  }

  @Override
  public String routingKey() {
    return "ORDER";
  }
}
