package com.yq.hook.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:07
 **/

/**
 * urlPatterns：指定一组过滤器的 URL 匹配模式
 * filterName：指定过滤器的 name 属性
 * asyncSupported：声明过滤器是否支持异步操作模式
 */

// 注册 Filter 方式 1
@Order(-1)
@WebFilter(urlPatterns = "/*",filterName = "reqFilter",asyncSupported = true)
public class ReqFilter implements Filter {

    private static Logger REQ_LOG = LoggerFactory.getLogger(ReqFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        filterChain.doFilter(request,servletResponse);

        long end = System.currentTimeMillis() - start;

        buildRequestLog(request,end);
    }

    @Override
    public void destroy() {

    }

    private void buildRequestLog(HttpServletRequest request,long costTime){
        if(request == null) return;

        StringBuilder msg = new StringBuilder();
        msg.append("method=").append(request.getMethod()).append("; ");
        msg.append("; uri=").append(request.getRequestURI());
        msg.append("; cost=").append(costTime);
        REQ_LOG.info("{}", msg.toString());
    }
}
