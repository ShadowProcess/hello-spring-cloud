package com.funtl.hello.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *  1. zuul1.x是一个机遇阻m塞I/O的API Gateway
 *
 *  2. zuul1.x机遇servlet2.5，使用阻塞架构，它不支持任何长连接，如websocket。zuul的设计模式和nginx较像，每次I/O操作都是从工作线程中选择一个执行，
 *     请求线程被阻塞到工作线程完成，但是差别是nginx用C++实现，zuul用java实现，而jvm本身会有第一次加载较慢的情况，使得zuul的性能相对较差
 *
 *  3. zuul2.x基于netty非阻塞、支持长连接，但Spring Cloud目前还没有整合。zuul2.x的性能较zuul1.x有较大提高。在性能方面，根据官网提供的基准测试，
 *     Spring Cloud Gateway的RPS（每秒请求数）是zuul的1.6倍
 *
 *  4. Spring Cloud Gateway 建立 在 Spring Framework 5、 Project Reactor 和 Spring Boot 2 之上， 使用 非阻塞 API。
 *
 *  5. Spring Cloud Gateway 还 支持 WebSocket， 并且 与 Spring紧密集成， 拥有更好的开发体验
 */

@SpringBootApplication
@EnableEurekaClient //开启Eureka客户端
@EnableZuulProxy    //开启Zuul
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }
}
