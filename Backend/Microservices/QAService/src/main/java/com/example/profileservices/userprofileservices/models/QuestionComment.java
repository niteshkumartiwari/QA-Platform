package com.example.profileservices.userprofileservices.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment_question", schema = "public")
public class QuestionComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "content")
    private String content;

    @Column(name = "upkudo")
    private Long upkudo;

    private transient Long quesId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "replied_by")
    private Long repliedBy;

    @Column(name= "created_on")
    private Date createdOn;

    public QuestionComment() {
        this.upkudo= Long.valueOf(0);
        this.createdOn=new Date(System.currentTimeMillis());
    }

    public QuestionComment(Long id, @NotBlank String content, Long upkudo, Date createdOn) {
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

    public Long getQuesId() {
        if(question!=null)
            quesId=question.getId();
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(Long repliedBy) {
        this.repliedBy = repliedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
