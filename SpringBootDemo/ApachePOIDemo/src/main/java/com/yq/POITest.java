package com.yq;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 09:43
 **/
public class POITest {

    private static final String PATH = "SpringBootDemo\\ApachePOIDemo\\src\\xlsx";

    public static void main(String[] args) throws IOException {
//        write();
        read();
    }


    public static void write() throws IOException {
        // 在内存中创建 Excel
        XSSFWorkbook excel = new XSSFWorkbook();
        // 在 Excel 中创建一个 Sheet 页
        XSSFSheet sheet = excel.createSheet("info");
        // 在 Sheet 中创建行对象，编号从 0 开始
        XSSFRow row_0 = sheet.createRow(0);
        // 创建单元格并写入文件内容，从 0 开始
        row_0.createCell(0).setCellValue("姓名");
        row_0.createCell(1).setCellValue("城市");

        XSSFRow row_1 = sheet.createRow(1);
        row_1.createCell(0).setCellValue("sumAll");
        row_1.createCell(1).setCellValue("shanghai");

        // 通过输出流将内存中的 Excel 文件写入磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(new File(PATH + "\\info.xlsx"));
        excel.write(fileOutputStream);

        // 关闭资源
        fileOutputStream.close();
        excel.close();
    }


    public static void read() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(PATH + "\\info.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = excel.getSheetAt(0);

        // 获取 sheet 页中最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        for(int i=0;i<=lastRowNum;i++){
            XSSFRow row = sheet.getRow(i);
            String value_0 = row.getCell(0).getStringCellValue();
            String value_1 = row.getCell(1).getStringCellValue();
            System.out.println(value_0 + "\t" + value_1);
        }

        fileInputStream.close();
        excel.close();
    }


}
