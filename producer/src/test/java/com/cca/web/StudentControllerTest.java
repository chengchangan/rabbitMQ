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

  /**
   * test
   */
  @Test
  public void direct() {
    studentController.direct();
  }

  @Test
  public void topicSendOne() {
    studentController.topicSendOne();
  }

  @Test
  public void topicSendTwo() {
    studentController.topicSendTwo();
  }

  @Test
  public void fanout() {
    studentController.fanout();
  }


  @Test
  public void test() {
    studentController.test();
  }

  /**
   * 错误提交的文件
   */
}