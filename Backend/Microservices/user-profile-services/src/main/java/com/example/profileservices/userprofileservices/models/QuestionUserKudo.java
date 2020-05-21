package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.QuestionUserKudoId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "question_user_kudo",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","question_id"})
})
public class QuestionUserKudo implements Serializable {
    @EmbeddedId
    private QuestionUserKudoId id;

    private transient Long usrId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private transient Long quesId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    @Column(name= "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public QuestionUserKudo() {
        this.createdOn=new Date(System.currentTimeMillis());
    }

    public QuestionUserKudo(QuestionUserKudoId id, User user, Question question) {
        this.id = id;
        this.user = user;
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public QuestionUserKudo(QuestionUserKudoId id) {
        this.id = id;
    }

    public QuestionUserKudoId getId() {
        return id;
    }

    public void setId(QuestionUserKudoId id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Question getQuestion() {
        return question;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
