package com.yq.hook.servlet;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 14:17
 **/

/**
 * 这种方式有坑
 * 定义两个同样方式注册的 Servlet，其路径为 beanName + /
 * 定义一个 bean 时，添加 @Order 更改其优先级，让其优先级高于 Spring Web 的 Servlet，从而将相同的 URL 优先分配给 BeanServlet
 */

@Component
@Order(-1)
public class BeanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("[BeanServlet] Welcome, " + name);
        writer.flush();
        writer.close();
    }

}
