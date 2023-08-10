package com.yq.pattern.prototype;

import com.yq.pattern.prototype.question.AnswerQuestion;
import com.yq.pattern.prototype.question.ChoiceQuestion;
import com.yq.pattern.prototype.util.Topic;
import com.yq.pattern.prototype.util.TopicRandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 试卷
 * @author: Yuqing
 * @create: 2023-08-10 11:10
 **/
public class QuestionBank implements Cloneable{

    /** 考生 */
    private String candidate;
    /** 考号 */
    private String number;

    /** 选择题 */
    private ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<ChoiceQuestion>();
    /** 简答题 */
    private ArrayList<AnswerQuestion> answerQuestionList = new ArrayList<AnswerQuestion>();

    public QuestionBank append(ChoiceQuestion choiceQuestion){
        choiceQuestionList.add(choiceQuestion);
        return this;
    }

    public QuestionBank append(AnswerQuestion answerQuestion){
        answerQuestionList.add(answerQuestion);
        return this;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 原型模型的核心方法
     * @return 乱序后的新试卷
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        QuestionBank questionBank = (QuestionBank) super.clone();
        questionBank.choiceQuestionList = (ArrayList<ChoiceQuestion>) this.choiceQuestionList.clone();
        questionBank.answerQuestionList = (ArrayList<AnswerQuestion>) this.answerQuestionList.clone();

        // 题目乱序
        Collections.shuffle(questionBank.choiceQuestionList);
        Collections.shuffle(questionBank.answerQuestionList);

        // 答案乱序
        ArrayList<ChoiceQuestion> list = questionBank.choiceQuestionList;
        for(ChoiceQuestion question: list){
            Topic topic = TopicRandomUtil.random(question.getOption(), question.getAnswer());
            question.setOption(topic.getOption());
            question.setAnswer(topic.getAnswer());
        }
        return questionBank;
    }


    @Override
    public String toString() {

        StringBuilder detail = new StringBuilder("考生：" + candidate + "\r\n" +
                "考号：" + number + "\r\n" +
                "--------------------------------------------\r\n" +
                "一、选择题" + "\r\n\n");

        for (int idx = 0; idx < choiceQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(choiceQuestionList.get(idx).getName()).append("\r\n");
            Map<String, String> option = choiceQuestionList.get(idx).getOption();
            for (String key : option.keySet()) {
                detail.append(key).append("：").append(option.get(key)).append("\r\n");;
            }
            detail.append("答案：").append(choiceQuestionList.get(idx).getAnswer()).append("\r\n\n");
        }

        detail.append("二、问答题" + "\r\n\n");

        for (int idx = 0; idx < answerQuestionList.size(); idx++) {
            detail.append("第").append(idx + 1).append("题：").append(answerQuestionList.get(idx).getQuestion()).append("\r\n");
            detail.append("答案：").append(answerQuestionList.get(idx).getAnswer()).append("\r\n\n");
        }

        return detail.toString();
    }

}
