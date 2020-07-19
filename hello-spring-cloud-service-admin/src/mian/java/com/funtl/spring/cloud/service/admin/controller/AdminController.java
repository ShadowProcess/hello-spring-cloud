package com.funtl.spring.cloud.service.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Value("${server.port}") //使用spring的el表达式，这个值会自动注入进来，就是这个服务启动使用的端口
    private String port;

    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHi(String message){
        return String.format("Hi your message is : %s port : %s",message,port);
    }
}
