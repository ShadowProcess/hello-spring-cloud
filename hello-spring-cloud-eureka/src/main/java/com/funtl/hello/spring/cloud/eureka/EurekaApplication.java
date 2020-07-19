package com.funtl.hello.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务的注册发现中心
 * 浏览器访问: http://localhost:8761/
 *
 * 该[Eureka]服务要部署多台，否则，一个挂了，就都挂了，不符合高可用，应该集群
 *
 */

@SpringBootApplication
@EnableEurekaServer //开启EurekaServer，用于发现客户服务端
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
