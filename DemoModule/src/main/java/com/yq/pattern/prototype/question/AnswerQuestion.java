package com.yq.pattern.prototype.question;

/**
 * @program: JavaDemoRep
 * @description: 解答题
 * @author: Yuqing
 * @create: 2023-08-10 10:49
 **/
public class AnswerQuestion {

    /** 问题 */
    private String question;
    /** 答案 */
    private String answer;

    public AnswerQuestion() {
    }

    public AnswerQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
