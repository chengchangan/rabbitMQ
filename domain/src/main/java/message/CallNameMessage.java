package message;

/**
 * 老师点名回答问题 ， 指定学生
 *
 * @author Shenzhen cca
 * @version 2018/9/16
 */
public class CallNameMessage extends Message {

  private Long studentId;

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  @Override
  public String exchange() {
    return Constants.DIRECT_EXCHANGE;
  }

  @Override
  public String routingKey() {
    return Constants.DIRECT_BINDING_KEY;
  }
}
