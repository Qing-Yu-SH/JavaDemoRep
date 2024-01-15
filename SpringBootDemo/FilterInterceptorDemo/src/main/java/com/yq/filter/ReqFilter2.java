package com.yq.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-12 09:13
 **/
@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "reqFilter2", asyncSupported = true)
public class ReqFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("reqFilter2 前置处理");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("reqFilter2 后置处理");
    }

}
