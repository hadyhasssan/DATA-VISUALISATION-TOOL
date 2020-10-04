package com.cordis.model;

public class Topic {

    private String topicCode;
    private String topicName;

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicCode='" + topicCode + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
