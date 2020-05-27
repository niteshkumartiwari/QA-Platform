package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.QuestionInterestId;

import javax.persistence.*;

@Entity
@Table(name = "question_interest",schema = "public")
public class QuestionInterest {
    @EmbeddedId
    private QuestionInterestId id;

    private transient Long quesId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    @Column(name = "interest_id", insertable = false, updatable = false)
    private Long interest;

    public QuestionInterest() {
    }

    public QuestionInterest(QuestionInterestId id, Question question) {
        this.id = id;
        this.question = question;
    }

    public QuestionInterestId getId() {
        return id;
    }

    public void setId(QuestionInterestId id) {
        this.id = id;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getInterest() {
        return interest;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }
}
