package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestionUserKudoId implements Serializable {
    @Column(name= "user_id")
    private Long userId;

    @Column(name = "question_id")
    private Long questionId;

    public QuestionUserKudoId() {
    }

    public QuestionUserKudoId(Long userId, Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnswerId() {
        return questionId;
    }

    public void setAnswerId(Long answerId) {
        this.questionId = answerId;
    }

    @Override
    public boolean equals(Object obj) {
        QuestionUserKudoId answerUserKudoId= (QuestionUserKudoId) obj;
        return ((QuestionUserKudoId) obj).getAnswerId()==questionId && ((QuestionUserKudoId) obj).userId==userId;
    }
}
