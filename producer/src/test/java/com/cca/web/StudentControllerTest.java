package com.cca.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/4/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

    @Autowired
    StudentController studentController;

    @Test
    public void topic() throws Exception {
        studentController.topic();
    }

    @Test
    public void fanout(){
        studentController.fanout();
    }


}