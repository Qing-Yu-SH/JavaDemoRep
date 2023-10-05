package com.yq.hook.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 14:01
 **/

/**
 * 创建 Servlet 的方式：
 * 1.使用注解 @WebServlet —— HealthServlet
 * 2.使用 ServletRegistrationBean —— ApplicationConfig
 * 3.使用 ServletContextInitializer —— SelfServletConfig
 * 4.将 Servlet 当做普通的 bean —— BeanServlet
 */

// 注册 Servlet 方式 1
@WebServlet(urlPatterns = "/check")
public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("ok");
        writer.flush();
        writer.close();
    }

}
