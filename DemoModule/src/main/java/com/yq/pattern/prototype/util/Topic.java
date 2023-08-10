package com.yq.pattern.prototype.util;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-10 10:53
 **/
public class Topic {

    private Map<String, String> option;
    private String answer;

    public Topic() {
    }

    public Topic(Map<String, String> option, String answer) {
        this.option = option;
        this.answer = answer;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public void setOption(Map<String, String> option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}