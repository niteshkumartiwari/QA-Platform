package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AnswerUserKudoId implements Serializable {
    @Column(name= "user_id")
    private Long userId;

    @Column(name = "answer_id")
    private Long answerId;

    public AnswerUserKudoId() {
    }

    public AnswerUserKudoId(Long userId, Long answerId) {
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
        AnswerUserKudoId answerUserKudoId= (AnswerUserKudoId) obj;
        return ((AnswerUserKudoId) obj).getAnswerId()==answerId && ((AnswerUserKudoId) obj).userId==userId;
    }
}
