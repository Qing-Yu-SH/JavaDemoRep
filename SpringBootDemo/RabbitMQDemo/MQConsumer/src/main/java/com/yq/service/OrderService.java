package com.yq.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 订单服务
 * @author: Yuqing
 * @create: 2023-10-16 19:27
 **/
@Service
public class OrderService {

    private static List<Order> orderList = new ArrayList<Order>(){{
        add(new Order(0L,1));
        add(new Order(1L,1));
        add(new Order(2L,1));
        add(new Order(3L,1));
        add(new Order(4L,1));
        add(new Order(5L,2));
        add(new Order(6L,2));
    }};

    public Order getById(Long id){
        if(id.intValue() >= orderList.size()){
            return null;
        }
        return orderList.get(id.intValue());
    }

    public void markOrderPaySuccess(Long id){
        Order order = orderList.get(id.intValue());
        order.setStatus(2);
    }

    public void markOrderPayFail(Long id){
        Order order = orderList.get(id.intValue());
        order.setStatus(5);
    }

    public void cancelOrder(Long id){
        Order order = orderList.get(id.intValue());
        order.setStatus(1);
    }

}
