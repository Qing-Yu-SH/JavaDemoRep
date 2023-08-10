package com.yq.pattern.prototype.util;

import java.util.*;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-10 10:54
 **/
public class TopicRandomUtil {

    /**
     * 乱序 Map元素，记录对应答案 answer
     * @param option 选项
     * @param answer 答案
     * @return 乱序后的选项
     */
    static public Topic random(Map<String,String> option, String answer){
        Set<String> keySet = option.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        Collections.shuffle(keyList);
        Map<String,String> newOption = new HashMap<>();
        int idx = 0;
        String newAnswer = "";
        for(String next: keySet){
            String randomKey = keyList.get(idx++);
            if(next.equals(answer)){
                newAnswer = randomKey;
            }
            newOption.put(randomKey,option.get(next));
        }
        return new Topic(newOption,newAnswer);
    }

}
