package com.yq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 13:20
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ReportService reportService;


    /**
     * 导出运营数据报表
     * @param response
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException, ParseException {
//        response.setHeader("Content-Disposition", "attachment");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        reportService.exportBusinessData(response);
    }

}
