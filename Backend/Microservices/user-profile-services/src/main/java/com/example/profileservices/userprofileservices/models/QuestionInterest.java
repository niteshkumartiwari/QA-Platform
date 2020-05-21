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

    private transient Long intrestId;

    @ManyToOne
    @JoinColumn(name = "interest_id", insertable = false, updatable = false)
    private Interest interest;

    public QuestionInterest() {
    }

    public QuestionInterest(QuestionInterestId id, Question question, Interest interest) {
        this.id = id;
        this.question = question;
        this.interest = interest;
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

    public Long getIntrestId() {
        return intrestId;
    }

    public void setIntrestId(Long intrestId) {
        this.intrestId = intrestId;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }
}
