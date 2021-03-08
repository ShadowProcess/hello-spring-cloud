package com.funtl.hello.spring.cloud.web.admin.feign.requesheaderproblem;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * Feign在请求时是不会将request的请求头带着请求的，导致假如Feign调用的接口需要请求头的信息，比如当前用户的 token 之类的就获取不到
 *
 * 那么我们就需要，把上个请求的请求头信息设置到Feign请求头里边，
 * 将请求头中的参数，全部作为 feign 请求头参数传递
 *
 *
 * 注意：<配置修改/>
 * 主要是 hystrix.command.default.execution.isolation 后面的配置，
 * 需要将 hystrix 配置为信号量模式，否则会出现由于隔离策略导致获取不到请求头
 */

/**
 * # hystrix 配置
 * hystrix:
 *   command:
 *     default:  #default全局有效，service id指定应用有效
 *       execution:
 *         timeout:
 *           #是否开启超时熔断
 *           enabled: true
 *         isolation:
 *           thread:
 *             timeoutInMilliseconds: 10000 #断路器超时时间，默认1000ms
 *           # hystrix 隔离模式改为信号量模式，feign 才能获取到父线程中的请求头
 *           strategy: SEMAPHORE
 *           # 允许的并发量，默认值为 10
 *           semaphore:
 *             maxConcurrentRequests: 100
 * ————————————————
 */

/**
 * Hystrix内部提供了两种模式执行逻辑：信号量、线程池。
 *
 * 来自hystrix官网
 * 默认情况下，Hystrix使用线程池模式。
 * 不过两者有什么区别，在实际场景中如何选择？
 *
 * 如果要使用信号量模式，
 * 需要配置参数execution.isolation.strategy = ExecutionIsolationStrategy.SEMAPHORE.
 *
 * 信号量模式
 * 在该模式下，接收请求和执行下游依赖在同一个线程内完成，不存在线程上下文切换所带来的性能开销，所以大部分场景应该选择信号量模式，但是在下面这种情况下，信号量模式并非是一个好的选择。
 *
 * 比如一个接口中依赖了3个下游：serviceA、serviceB、serviceC，且这3个服务返回的数据互相不依赖，这种情况下如果针对A、B、C的熔断降级使用信号量模式，那么接口耗时就等于请求A、B、C服务耗时的总和，无疑这不是好的方案。
 */
public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Enumeration<String> headerNames = request().getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request().getHeader(name);
                requestTemplate.header(name,value); //将上个请求的请求头信息，设置到feign本次请求里边，因为feign的默认请求是不带请求头的
            }
        }
    }


    public static HttpServletRequest request(){
        return  ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}


