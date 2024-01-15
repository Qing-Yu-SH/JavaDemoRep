package com.yq.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-12 09:11
 **/
@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "reqFilter", asyncSupported = true)
public class ReqFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("reqFilter 前置处理");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("reqFilter 后置处理");
    }

}
