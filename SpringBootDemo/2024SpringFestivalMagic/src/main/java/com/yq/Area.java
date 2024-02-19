package com.yq;

/**
 * @program: JavaDemoRep
 * @description: 地域
 * @author:
 * @create: 2024-02-19 23:39
 **/
public enum Area {

    SOUTH("南方人",1),
    NORTH("北方人",2),
    UNKNOWN("不知",3);

    private String areaName;
    private int num;

    Area(String areaName,int num){
        this.areaName = areaName;
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
