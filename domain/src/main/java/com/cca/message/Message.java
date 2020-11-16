package com.cca.message;

/**
 * Created by Administrator on 2018/4/21.
 */
public abstract class Message {

  public abstract String exchange();

  public abstract String routingKey();

}
