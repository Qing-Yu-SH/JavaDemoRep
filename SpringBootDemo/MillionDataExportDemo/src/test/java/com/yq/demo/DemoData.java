package com.yq.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yq.util.DateConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 19:48
 **/
@Getter
@Setter
@EqualsAndHashCode
public class DemoData {

    private String string;
    @ExcelProperty(converter = DateConverter.class)
    private Date date;
    private Double doubleData;

}
