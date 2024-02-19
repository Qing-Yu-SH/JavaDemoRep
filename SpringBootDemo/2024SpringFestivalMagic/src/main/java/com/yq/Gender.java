package com.yq;

/**
 * @program: JavaDemoRep
 * @description: 性别
 * @author:
 * @create: 2024-02-20 00:04
 **/
public enum Gender {

    BOY("男孩",1),
    GIRL("女孩",2);

    private String gender;
    private int num;

    Gender(String gender,int num){
        this.gender = gender;
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
