package com.funtl.hello.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//Zuul的过滤功能演示

@Component
public class LoginFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * filterType
     *  返回一个代表过滤器的类型，在Zuul中定义了四种不同生命周期的过滤类型
     *  pre:路由之前
     *  routing:路由之时
     *  post:路由之后
     *  error:发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }


    /**
     * filterOrder
     *    过滤的顺序,数字越小，越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }


    /**
     * shouldFilter
     *  是否需要过滤，这里是true，需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务代码(使用Zuul进行权限校验)
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        logger.info("{} >>> {}",request.getMethod(),request.getRequestURL().toString());

        String token = request.getParameter("token");
        if (token == null) {
            logger.warn("Token is Empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);

            try {
                HttpServletResponse response = context.getResponse();
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("Token 是空的");
            } catch (IOException e) {

            }
        } else {
            logger.info("ok");
        }
        return null;
    }

}
