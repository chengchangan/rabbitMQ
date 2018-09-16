package com.cca.web;

import com.cca.MessageSender;
import message.CallNameMessage;
import message.ClearClassRoomOneMessage;
import message.ClearClassRoomTwoMessage;
import message.GoToClassMessage;
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
}
