package message;

/**
 * 上课了 ， 所有人听到铃声都得回到教室
 *
 * @author Shenzhen cca
 * @version 2018/9/16
 */
public class GoToClassMessage extends Message {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String exchange() {
    return Constants.FANOUT_EXCHANGE;
  }

  @Override
  public String routingKey() {
    return null;
  }
}
