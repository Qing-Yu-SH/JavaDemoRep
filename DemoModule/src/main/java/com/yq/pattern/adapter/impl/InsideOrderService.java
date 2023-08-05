package com.yq.pattern.adapter.impl;

import com.yq.pattern.adapter.OrderAdapterService;
import com.yq.pattern.adapter.service.OrderService;

/**
 * @program: JavaDemoRep
 * @description: 内部商品接⼝
 * @author: Yuqing
 * @create: 2023-08-05 16:00
 **/
public class InsideOrderService implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    @Override
    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1L;
    }

}
