package com.yq;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.yq.demo.DemoData;
import com.yq.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 19:34
 **/
@Slf4j
@SpringBootTest
public class EasyExcelTest {

    private final String PATH = "/xlsx";


    @Test
    public void test_testFileUtil() {
        String fileName = TestFileUtil.getPath() + "xlsx" + File.separator + "demo.xlsx";
        String path = "E:\\FutureArchitect\\项目\\JavaDemoRep\\SpringBootDemo\\MillionDataExportDemo\\target\\classes\\xlsx\\demo.xlsx";
        System.out.println(fileName);
        System.out.println(path);
    }


    @Test
    public void test_read() {
        String fileName = TestFileUtil.getPath() + "xlsx" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            for (DemoData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }
        })).sheet().doRead();
    }

    @Test
    public void test_write(){
        String fileName = TestFileUtil.getPath() + "xlsx" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }

    private List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


}
