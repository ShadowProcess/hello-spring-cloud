package com.funtl.hello.spring.cloud.zuul.ratelimit;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class RateLimitConfig {

    @Bean
    public RateLimiterErrorHandler rateLimiterErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {
            /**
             * 往你设置的限流信息存储位置,限流请求信息的时候报错的处理，此方法一般不用重写
             * @param key
             * @param e
             */
            @Override
            public void handleSaveError(String key, Exception e) {
                super.handleSaveError(key, e);
            }

            /**
             * 从你设置的限流信息存储位置,取出限流请求信息的时候报错的处理，此方法一般不用重写
             * @param key
             * @param e
             */
            @Override
            public void handleFetchError(String key, Exception e) {
                super.handleFetchError(key, e);
            }

            /**
             * 请求发生限流了，或者限流过程中发生错误了的处理
             * 对限流进行日志记录，返回限流的信息等，方便后期分析日志排查问题，这里就简单处理打印日志
             * @param msg
             * @param e
             */
            @Override
            public void handleError(String msg, Exception e) {
                System.out.println("限流信息:" + msg + "错误信息:" + e);
                super.handleError(msg, e);
            }
        };
    }
}
