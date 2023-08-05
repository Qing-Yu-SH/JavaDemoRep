package com.yq.pattern.adapter.impl;

import com.yq.pattern.adapter.OrderAdapterService;
import com.yq.pattern.adapter.service.POPOrderService;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-05 16:04
 **/
public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    @Override
    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}
