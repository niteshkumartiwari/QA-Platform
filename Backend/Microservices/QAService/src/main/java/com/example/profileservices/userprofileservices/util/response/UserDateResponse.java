package com.example.profileservices.userprofileservices.util.response;

import java.util.Date;

public class UserDateResponse {
    Long userId;
    Date createdOn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
