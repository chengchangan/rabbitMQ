package com.cca.web;

import com.alibaba.fastjson.JSONObject;
import com.cca.MessageSender;
import java.util.Date;
import com.cca.message.CallNameMessage;
import com.cca.message.ClearClassRoomOneMessage;
import com.cca.message.ClearClassRoomTwoMessage;
import com.cca.message.GoToClassMessage;
import com.cca.message.TestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/21.
 */
@RestController
@RequestMapping("api")
@Component
public class StudentController {

    @Autowired
    MessageSender messageSender;


    public void direct(){
        CallNameMessage message = new CallNameMessage();
        message.setStudentId(1L);
        messageSender.send(message);
    }

    public void fanout(){
        GoToClassMessage message = new GoToClassMessage();
        message.setMessage("上课铃响了，所有学生进教室");
        messageSender.send(message);
    }

    public void topicSendOne(){
        ClearClassRoomOneMessage message = new ClearClassRoomOneMessage();
        message.setClearRoomMessage("今天值日生开始打扫卫生");
        messageSender.send(message);
    }

    public void topicSendTwo(){
        ClearClassRoomTwoMessage message = new ClearClassRoomTwoMessage();
        message.setClearRoomMessage("今天值日生开始打扫卫生");
        messageSender.send(message);
    }



    public void test (){
        TestMessage message = new TestMessage();
        message.setMessage(sendCreateOrderMessage());
        messageSender.send(message);

    }

    public String sendCreateOrderMessage() {
        JSONObject message =  new JSONObject();
        message.put("order_code", "123");
        message.put("trade_id", "123");
        message.put("event", "ORDER_CREATE");
        message.put("storeName", "美甲帮商城");
        message.put("timestamp", (new Date()).getTime()/1000);
        return message.toJSONString();
        //sendMessage("ORDER", message.toString());
    }
}
