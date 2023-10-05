package com.yq.hook.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:28
 **/
public class ZFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ZFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("ZFilter 开始执行..");
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("ZFilter 执行结束..");
    }

}
