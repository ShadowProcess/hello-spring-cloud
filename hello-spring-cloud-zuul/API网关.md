当您选择将应用程序构建成为一组微服务时，您需要决定应用程序客户端将如何与微服务进行交互
单体应用程序只有一组端点（endpoint）,通常使用复制(replicated)
结合负载均衡来分配流量

然而，在微服务架构中，每个微服务都暴露一组通常比较细颗粒的端点，
在本文中，我们将研究如何改进客户端通信，并提出一个使用API网关的方案


###在spring Cloud微服务系统中，一种常见的负载方式是，客户端的请求首先经过负载均衡（Nginx）服务器,再到达服务网关（Zuul集群），然后再到具体的服务
###服务统一注册到高可用的服务注册中心集群，服务的所有配置文件由配置服务管理，配置服务的配置文件放在Git仓库，方便开发人员随时修改
Zuul简介：
Zuul的主要功能是路由转发和过滤器，路由功能是微服务的一部分，比如/api/user转发到User服务 ,/api/shop转发到Shop服务，
Zuul默认和Ribbon结合实现了负载均衡的功能


使用浏览器访问：
http://localhost:8769/api/a/hi?message=123

http://localhost:8769/api/b/hi?message=123

可能会出现一会可以访问，一会不能访问的情况
这就是所谓的网络不可靠！
我们在本机启动了5个服务，导致计算机性能也下降了

我们未来将其部署到docker上，放在五个服务器上
