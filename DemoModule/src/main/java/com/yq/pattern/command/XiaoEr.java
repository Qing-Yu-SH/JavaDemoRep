package com.yq.pattern.command;

import com.yq.pattern.command.command.ICuisine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 调⽤者 - 小二
 * @author: Yuqing
 * @create: 2023-08-16 10:22
 **/
public class XiaoEr {

    private Logger logger = LoggerFactory.getLogger(XiaoEr.class);
    private List<ICuisine> cuisineList = new ArrayList<ICuisine>();

    /**
     * 点单
     */
    public void order(ICuisine cuisine){
        cuisineList.add(cuisine);
    }

    /**
     * 上菜
     */
    public synchronized void placeOrder(){
        for(ICuisine cuisine: cuisineList){
            cuisine.cook();
        }
        cuisineList.clear();
    }

}
