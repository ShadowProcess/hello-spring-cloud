#Feign简介
Feign是一个声明式的伪Http客户端，它使得写Http客户端变得简单
使用Feign,只需要创建一个接口并注解，它具有可插拔的注解特性，可使用Feign注解和JAX-RS注解
Feign支持可插拔的编码器和解码器，Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果

Feign采用的是基于接口的注解
Feign整合了Ribbon



###实际开发过程中使用Feign,因为Feign默认集成了Ribbon


###Feign  HTTP客户端负载均衡器  【feign用于服务与服务之间的调用】