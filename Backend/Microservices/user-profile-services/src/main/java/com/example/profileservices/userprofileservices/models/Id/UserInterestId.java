package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserInterestId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "interest_id")
    private Long interestId;

    public UserInterestId() {
    }

    public UserInterestId(Long userId, Long interestId) {
        this.userId = userId;
        this.interestId = interestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    @Override
    public boolean equals(Object obj) {
        UserInterestId userInterestId= (UserInterestId)obj;
        return userInterestId.getInterestId()==interestId && userInterestId.getUserId()==userId;
    }
}
