package com.example.profileservices.userprofileservices.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment_answer", schema = "public")
public class AnswerComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "content")
    private String content;

    @Column(name = "upkudo")
    private Long upkudo;

    private transient Long ansId;


    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private transient Long replyBy;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "replied_by")
    private User repliedBy;

    @Column(name= "created_on")
    private Date createdOn;

    public AnswerComment() {
        this.upkudo= Long.valueOf(0);
        this.createdOn=new Date(System.currentTimeMillis());
    }

    public AnswerComment(Long id, @NotBlank String content, Long upkudo, Date createdOn) {
        this.id = id;
        this.content = content;
        this.upkudo = upkudo;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUpkudo() {
        return upkudo;
    }

    public void setUpkudo(Long upkudo) {
        this.upkudo = upkudo;
    }

    public Long getAnsId() {
        return ansId;
    }

    public void setAnsId(Long ansId) {
        this.ansId = ansId;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Long getReplyBy() {
        return replyBy;
    }

    public void setReplyBy(Long replyBy) {
        this.replyBy = replyBy;
    }

    public void setRepliedBy(User repliedBy) {
        this.repliedBy = repliedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getRepliedBy() {
        return repliedBy;
    }
}
