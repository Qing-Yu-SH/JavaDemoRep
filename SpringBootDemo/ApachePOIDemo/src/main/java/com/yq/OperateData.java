package com.yq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-06 13:35
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperateData {

    private Date startDate;
    private Date endDate;
    private BigDecimal turnover;
    private Integer validOrderNum;
    private BigDecimal percentFinish;
    private BigDecimal perCustomerTransaction;
    private Integer newCustomerNum;

}
