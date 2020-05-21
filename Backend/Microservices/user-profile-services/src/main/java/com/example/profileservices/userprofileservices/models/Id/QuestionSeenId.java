package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestionSeenId implements Serializable {
    @Column(name= "user_id")
    private Long userId;

    @Column(name = "question_id")
    private Long questionId;

    public QuestionSeenId() {
    }

    public QuestionSeenId(Long userId, Long questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long answerId) {
        this.questionId = answerId;
    }

    @Override
    public boolean equals(Object obj) {
        QuestionSeenId questionSeenId= (QuestionSeenId) obj;
        return questionSeenId.getUserId()==userId && questionSeenId.getQuestionId()==questionId;
    }
}
