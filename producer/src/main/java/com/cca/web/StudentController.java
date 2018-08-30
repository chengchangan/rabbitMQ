package com.cca.web;

import com.cca.MessageSender;
import message.StudentMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    AmqpTemplate amqpTemplate;

    @RequestMapping("/test/{id}")
    public void test(@PathVariable String id){
        StudentMessage message = new StudentMessage();
        message.setName("张三"+id);
        message.setAge(20);
//        for (int i = 0 ; i<100; i++){
//            message.setAge(message.getAge()+1);
            messageSender.send(message);
//        }
        System.out.println("=============="+id);

    }

    public void test1(){
        StudentMessage message = new StudentMessage();
        message.setName("张三");
        message.setAge(202);
        messageSender.send(message);
    }
}
