package com.cca.message;

/**
 * Created by Administrator on 2018/4/21.
 */
public abstract class Message {

  /**
   * 延迟分钟数
   */
  private Integer delayMinutes;

  public Integer getDelayMinutes() {
    return delayMinutes;
  }

  public void setDelayMinutes(Integer delayMinutes) {
    this.delayMinutes = delayMinutes;
  }

  public abstract String exchange();

  public abstract String routingKey();

}
