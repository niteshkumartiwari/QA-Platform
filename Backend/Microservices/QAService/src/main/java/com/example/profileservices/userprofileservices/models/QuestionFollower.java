package com.example.profileservices.userprofileservices.models;

import com.example.profileservices.userprofileservices.models.Id.QuestionFollowerId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question_follower",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","question_id"})
})
public class QuestionFollower implements Serializable {
    @EmbeddedId
    private QuestionFollowerId id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long user;

    private transient Long quesId;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    public QuestionFollower() {
    }

    public QuestionFollower(QuestionFollowerId questionFollowerId, Long user, Question question) {
        this.id = questionFollowerId;
        this.user = user;
        this.question = question;
    }

    public QuestionFollowerId getId() {
        return id;
    }

    public void setId(QuestionFollowerId id) {
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
}
