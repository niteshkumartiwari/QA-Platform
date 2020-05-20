package com.example.profileservices.userprofileservices.util.retobjects;

import java.util.Date;
import java.util.List;

public class UserKudo {
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
