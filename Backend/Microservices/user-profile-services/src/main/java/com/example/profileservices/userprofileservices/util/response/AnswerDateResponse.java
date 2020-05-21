package com.example.profileservices.userprofileservices.util.response;

import java.util.Date;

public class AnswerDateResponse {
    Long answerId;
    Date createdOn;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
