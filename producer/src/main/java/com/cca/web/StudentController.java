package com.cca.web;

import com.alibaba.fastjson.JSONObject;
import com.cca.MessageSender;

import java.time.LocalDateTime;
import java.util.Date;

import com.cca.message.*;
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

    public void directDelay(Integer delayMinutes){
        DelayMessage message = new DelayMessage();
        message.setDelayMinutes(delayMinutes);
        message.setMessage("我是一个延迟消息，发送时间："+LocalDateTime.now());
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




}
