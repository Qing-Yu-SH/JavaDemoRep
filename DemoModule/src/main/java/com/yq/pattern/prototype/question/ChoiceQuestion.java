package com.yq.pattern.prototype.question;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 单选题
 * @author: Yuqing
 * @create: 2023-08-10 10:47
 **/
public class ChoiceQuestion {

    /** 题目 */
    private String name;
    /** 选项；A、B、C、D */
    private Map<String, String> option;
    /** 答案 */
    private String answer;

    public ChoiceQuestion() {
    }

    public ChoiceQuestion(String name, Map<String, String> option, String answer) {
        this.name = name;
        this.option = option;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
