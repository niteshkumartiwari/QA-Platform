package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "answer_user_kudo",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","answer_id"})
})
public class AnswerUserKudo implements Serializable {
    @EmbeddedId
    private AnswerUserKudoId id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user;

    private transient Long ansId;

    @ManyToOne
    @JoinColumn(name = "answer_id", insertable = false, updatable = false)
    private Answer answer;

    @Column(name= "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public AnswerUserKudo() {
        this.createdOn=new Date(System.currentTimeMillis());
    }

    public AnswerUserKudo(AnswerUserKudoId id, Long user, Answer answer) {
        this.id = id;
        this.user = user;
        this.answer = answer;
    }

    public Long getUser() {
        return user;
    }

    public AnswerUserKudo(AnswerUserKudoId id) {
        this.id = id;
    }

    public AnswerUserKudoId getId() {
        return id;
    }

    public void setId(AnswerUserKudoId id) {
        this.id = id;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Long getAnsId() {
        return ansId;
    }

    public void setAnsId(Long ansId) {
        this.ansId = ansId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
