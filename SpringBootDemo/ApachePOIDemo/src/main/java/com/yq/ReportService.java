package com.yq;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 13:21
 **/
@Service
public class ReportService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public void exportBusinessData(HttpServletResponse response) throws IOException, ParseException {
        // 1.查询数据
        List<OperateData> datas = getOperateData();


        // 2.通过 POI 将数据写入 Excel 文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = excel.getSheetAt(0);
        int startIndex = 5;

        for(OperateData operateData: datas){
            XSSFRow row = sheet.getRow(startIndex++);
            row.getCell(0).setCellValue(sdf.format(operateData.getStartDate()) + " 至 " + sdf.format(operateData.getEndDate()));
            row.getCell(1).setCellValue(operateData.getTurnover().toString());
            row.getCell(2).setCellValue(operateData.getValidOrderNum());
            row.getCell(3).setCellValue(operateData.getPercentFinish().toString() + "%");
            row.getCell(4).setCellValue(operateData.getPerCustomerTransaction().toString());
            row.getCell(5).setCellValue(operateData.getNewCustomerNum());
        }


        // 3.通过输出流将 Excel 文件下载到客户端
        ServletOutputStream outputStream = response.getOutputStream();
        excel.write(outputStream);

        outputStream.close();
        excel.close();
    }

    private List<OperateData> getOperateData() throws ParseException {
        return new ArrayList<OperateData>(){{
           add(new OperateData(sdf.parse("2023-11-01"),sdf.parse("2023-11-05"),new BigDecimal("220000"),200,new BigDecimal("89.2"),new BigDecimal("280.6"),200));
           add(new OperateData(sdf.parse("2023-11-06"),sdf.parse("2023-11-10"),new BigDecimal("260000"),220,new BigDecimal("92.2"),new BigDecimal("282.6"),280));
        }};
    }


}
