package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.QuestionSeenId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "seen_question",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","question_id"})
})
public class QuestionSeen implements Serializable {
    @EmbeddedId
    private QuestionSeenId id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user;

    private transient Long quesId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    @Column(name= "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public QuestionSeen() {
        this.createdOn = new Date(System.currentTimeMillis());
    }

    public QuestionSeen(QuestionSeenId id, Long user, Question question, Date createdOn) {
        this.id = id;
        this.user = user;
        this.question = question;
        this.createdOn = createdOn;
    }

    public QuestionSeenId getId() {
        return id;
    }

    public void setId(QuestionSeenId id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
