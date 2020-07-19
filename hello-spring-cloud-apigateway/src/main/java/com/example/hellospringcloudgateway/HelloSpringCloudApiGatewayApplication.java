package com.example.hellospringcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 1. zuul1.x是一个机遇阻m塞I/O的API Gateway

 2. zuul1.x机遇servlet2.5，使用阻塞架构，它不支持任何长连接，如websocket。zuul的设计模式和nginx较像，每次I/O操作都是从工作线程中选择一个执行，
    请求线程被阻塞到工作线程完成，但是差别是nginx用C++实现，zuul用java实现，而jvm本身会有第一次加载较慢的情况，使得zuul的性能相对较差

 3. zuul2.x基于netty非阻塞、支持长连接，但Spring Cloud目前还没有整合。zuul2.x的性能较zuul1.x有较大提高。在性能方面，根据官网提供的基准测试，
    Spring Cloud Gateway的RPS（每秒请求数）是zuul的1.6倍

 4. Spring Cloud Gateway 建立 在 Spring Framework 5、 Project Reactor 和 Spring Boot 2 之上， 使用 非 阻塞 API。

 5. Spring Cloud Gateway 还 支持 WebSocket， 并且 与 Spring紧密集成， 拥有更好的开发体验

 下面总结了一些gateway的特性：
 基于java8编码；
 基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建；
 支持动态路由，能够匹配任务请求属性上的路由；
 支持内置到Spring Handler映射中的路由配置；
 支持基于HTTP请求的路由匹配（Path，Method，Header，Host等）；
 集成了Hystrix断路器；
 过滤器作用于匹配的路由；
 过滤器可以修改HTTP请求和HTTP响应（增加/修改 头部，增加/修改 请求参数，改写请求路径等）；
 支持Spring Cloud DiscoveryClient配置路由，与服务发现与注册配合使用。
 支持限流
 支持地址重写
 */

@SpringBootApplication
public class HelloSpringCloudApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringCloudApiGatewayApplication.class, args);
    }


    /**
     * 路由配置
     * 浏览器访问： localhost:8080/baidu  将被路由到  http://baidu.com:80/
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        /**
         * apiGateway配置路由主要有两种方式，一种是用yml配置文件，一种是写代码里，这两种方式都是不支持动态配置的
         *
         * id：我们自定义的路由 ID，保持唯一  [一个RouteDefinition有个唯一的ID，如果不指定，就默认是UUID，多个RouteDefinition组成了gateway的路由系统。]
         * uri：目标服务地址
         * predicates：路由条件，Predicate 接受一个输入参数，返回一个布尔值结果。该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）。
         * filters：过滤规则，
         */
        return builder.routes()
                .route(r -> r.path("/baidu")
                        .uri("http://baidu.com:80/")
                )
                .route("websocket_route", r -> r.path("/apitopic1/**")
                        .uri("ws://127.0.0.1:6605"))
                .route(r -> r.path("/userapi3/**")
                        .filters(f -> f.addResponseHeader("X-AnotherHeader", "testapi3"))
                        .uri("lb://user-service/")
                )
                .build();
    }

}
