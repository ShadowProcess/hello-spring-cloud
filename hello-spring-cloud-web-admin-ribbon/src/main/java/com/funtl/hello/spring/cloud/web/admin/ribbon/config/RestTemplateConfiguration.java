package com.funtl.hello.spring.cloud.web.admin.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //这个配置相当于，原来spring-context.xml里边的配置  //这是java配置形式
public class RestTemplateConfiguration {

    @Bean //相当于原来xml文件创建了一个bean节点,指定class的位置上
    @LoadBalanced //我们用这个访问负载均衡服务 加上这个注解，他就自己去找服务提供者
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
