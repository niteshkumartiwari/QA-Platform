package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AnswerSeenId implements Serializable {
    @Column(name= "user_id")
    private Long userId;

    @Column(name = "answer_id")
    private Long answerId;

    public AnswerSeenId() {
    }

    public AnswerSeenId(Long userId, Long answerId) {
        this.userId = userId;
        this.answerId = answerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object obj) {
        AnswerSeenId answerSeenId= (AnswerSeenId) obj;
        return ((AnswerSeenId) obj).getAnswerId()==answerId && ((AnswerSeenId) obj).getUserId()==userId;
    }
}
