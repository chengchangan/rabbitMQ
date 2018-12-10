package com.cca.controller;

import com.cca.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shenzhen cca
 * @version 2018/12/10
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

  @Autowired
  MessageSender messageSender;


  @GetMapping()
  public void test(){

    //1
  }

}
