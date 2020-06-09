package com.example.profileservices.userprofileservices.models.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuestionInterestId implements Serializable {
    @Column(name= "question_id")
    private Long questionId;

    @Column(name= "interest_id")
    private Long interestId;

    public QuestionInterestId() {
    }

    public QuestionInterestId(Long questionId, Long interestId) {
        this.questionId = questionId;
        this.interestId = interestId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getInterestId() {
        return interestId;
    }

    public void setInterestId(Long interestId) {
        this.interestId = interestId;
    }

    @Override
    public boolean equals(Object obj) {
        QuestionInterestId questionInterestId= (QuestionInterestId) obj;
        return  questionInterestId.getQuestionId()== questionId && questionInterestId.getInterestId()==interestId;
    }
}
