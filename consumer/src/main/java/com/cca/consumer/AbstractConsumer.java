package com.cca.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.cca.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumer.class);


  public <T extends Message> void run(T message, final Channel channel, long tag, Runnable runnable)
      throws IOException {
    try {
      runnable.run();
      // 该处的　true/false 表示是否批量签收
      channel.basicAck(tag, true);
    } catch (RuntimeException e) {
      LOGGER.error("消息处理异常！{},{},{}", this.getClass().getName(), message, e.getMessage());
      // 重新放回队列
      channel.basicReject(tag, true);
    } catch (Exception e) {
      LOGGER.error("消息处理异常！{},{}", this.getClass().getName(), JSON.toJSONString(message));
      LOGGER.error("消息处理异常！", e);
      // 消息抛弃
      channel.basicReject(tag, false);
    }
  }
}
