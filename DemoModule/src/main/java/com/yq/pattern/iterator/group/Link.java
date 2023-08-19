package com.yq.pattern.iterator.group;

/**
 * @program: JavaDemoRep
 * @description: 链路
 * @author: Yuqing
 * @create: 2023-08-19 09:45
 **/
public class Link {

    private String fromId;
    private String toId;

    public Link() {
    }

    public Link(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

}
