package com.example.profileservices.userprofileservices.util.response;

import java.util.Date;

public class QuestionDateResponse {
    Long questionId;
    Date createdOn;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
