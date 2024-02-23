package com.xiaolv.music.serve.controller;

import com.xiaolv.music.serve.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GreetingController
 * @Description: TODO
 * @Author: lijinpeng
 * @Date: 2024/1/29 11:16
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @MessageMapping("/hello")
    @SendTo("/topic/ServerToClient.hello")
    public String greeting(Message message) throws Exception {
        System.out.println(message.getName());
        Thread.sleep(1000); // simulated delay
        return message.getName();
    }

    @RequestMapping("/h")
    public String hello(Message message) throws Exception {
        System.out.println("Hello, 欢迎您!");
        return new String("Hello, 欢迎您!");
    }


}
