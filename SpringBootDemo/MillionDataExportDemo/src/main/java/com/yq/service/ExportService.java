package com.yq.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yq.dao.IUserOrderDao;
import com.yq.pojo.UserOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 22:34
 **/
@Service
public class ExportService {

    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Resource
    private IUserOrderDao userOrderDao;

    public void exportExcel(HttpServletResponse response) throws IOException, InterruptedException {
        // 设置响应头
        setResponseHeader(response);

        // 多线程分页查询
        Map<Integer, Page<UserOrder>> pageMap = getIntegerPageMap();

        // 导出
        doExport(response, pageMap);
    }

    private static void setResponseHeader(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "UserOrder.com_export_100w.xlsx");
    }

    private static void doExport(HttpServletResponse response, Map<Integer, Page<UserOrder>> pageMap) throws IOException {
        // 导出，导出为什么不使用多线程呢？因为easyExcel 没有提供这样的能力
        // https://github.com/alibaba/easyexcel/issues/1040
        try (ExcelWriter excelWriter = EasyExcelFactory.write(response.getOutputStream(), UserOrder.class).build()) {
            for (Map.Entry<Integer, Page<UserOrder>> entry : pageMap.entrySet()) {
                Integer num = entry.getKey();
                Page<UserOrder> salariesPage = entry.getValue();
                WriteSheet writeSheet = EasyExcelFactory.writerSheet(num, "sheet-" + num).build();
                excelWriter.write(salariesPage, writeSheet);
            }
        }
    }

    private Map<Integer, Page<UserOrder>> getIntegerPageMap() throws InterruptedException {
        Long count = 100000L;

        Integer pages = 20;
        int size = (int) (count / pages);

        ExecutorService executorService = Executors.newFixedThreadPool(pages);
        CountDownLatch countDownLatch = new CountDownLatch(pages);

        // 这里直接放到内存中了，生产的话需要评估数据量的大小会不会造成内存溢出
        Map<Integer, Page<UserOrder>> pageMap = new HashMap<>();
        for (int i = 0; i < pages; i++) {
            int finalI = i;
            executorService.submit(() -> {
//                Page<UserOrder> page = new Page<>();
//                page.setPages(finalI);
//                page.setPageNum(finalI + 1);
//                page.setTotal(size);
                PageHelper.startPage(finalI,size);
                Page<UserOrder> selectPage = userOrderDao.selectByPage();

//                Page<UserOrder> selectPage = salariesMapper.selectPage(page, null);

                pageMap.put(finalI, selectPage);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        executorService.shutdown();
        return pageMap;
    }


}
