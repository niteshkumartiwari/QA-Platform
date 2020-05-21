package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.AnswerSeenId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "seen_answer",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","answer_id"})
})
public class AnswerSeen implements Serializable {
    @EmbeddedId
    private AnswerSeenId id;

    private transient Long usrId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    private transient Long ansId;

    @ManyToOne
    @JoinColumn(name = "answer_id", insertable = false, updatable = false)
    private Answer answer;

    @Column(name= "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public AnswerSeen() {
        this.createdOn= new Date(System.currentTimeMillis());
    }

    public AnswerSeen(AnswerSeenId id, User user, Answer answer) {
        this.id = id;
        this.user = user;
        this.answer = answer;
    }

    public AnswerSeenId getId() {
        return id;
    }

    public void setId(AnswerSeenId id) {
        this.id = id;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAnsId() {
        return ansId;
    }

    public void setAnsId(Long ansId) {
        this.ansId = ansId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
