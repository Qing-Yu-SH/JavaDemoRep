package com.yq.controller;

import com.github.pagehelper.Page;
import com.yq.pojo.UserOrder;
import com.yq.service.ExportService;
import com.yq.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 21:57
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/select/{page}/{limit}")
    public Page<UserOrder> select(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        return userOrderService.getUserOrderList(page, limit);
    }

    @GetMapping("export")
    public void exportExcel(HttpServletResponse response) throws IOException, InterruptedException {
        exportService.exportExcel(response);
    }


}
