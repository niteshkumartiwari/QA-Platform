package com.example.profileservices.userprofileservices.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "answer",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"question_id","answered_by"})
})
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_image")
    private Short isImage;

    @NotNull
    @Column(name = "res_link")
    private String resourceLink;

    @NotNull
    @Column(name = "caption")
    private String caption;

    @Column(name = "views")
    private Long viewCount;

    @Column(name = "upkudo")
    private Long upKudo;

    @Column(name = "downkudo")
    private Long downKudo;

    @Column(name = "is_deleted")
    private Short isDeleted;

    @Column(name = "is_shattered")
    private Short isShattered;

    @Column(name = "created_on")
    private Date createdAt;

    @Column(name = "last_updated")
    private Date modifiedAt;

    @Column(name = "thumbnail")
    private String thumbnail;

    //creating transient field for answered_by
    private transient Long usrId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "answered_by")
    private User answeredBy;

    //creating transient field for question_id
    private transient Long questId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer",fetch = FetchType.LAZY)
    private Set<AnswerUserKudo> answerUserKudos;

    public Answer() {
        this.isImage=0;
        this.viewCount= Long.valueOf(0);
        this.upKudo=Long.valueOf(0);
        this.downKudo= Long.valueOf(0);
        this.isDeleted= 0;
        this.isShattered= 0;
        this.createdAt= new Date(System.currentTimeMillis());
        this.modifiedAt=new Date(System.currentTimeMillis());
    }

    public Answer(Long id, Short isImage, @NotNull String resourceLink, @NotNull String caption,
                  Long viewCount, Long upKudo, Long downKudo, Short isDeleted, Short isShattered,
                  Date createdAt, Date modifiedAt, String thumbnail, User answeredBy, Question question) {
        this.id = id;
        this.isImage = isImage;
        this.resourceLink = resourceLink;
        this.caption = caption;
        this.viewCount = viewCount;
        this.upKudo = upKudo;
        this.downKudo = downKudo;
        this.isDeleted = isDeleted;
        this.isShattered = isShattered;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.thumbnail = thumbnail;
        this.answeredBy = answeredBy;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getIsImage() {
        return isImage;
    }

    public void setIsImage(Short isImage) {
        this.isImage = isImage;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getUpKudo() {
        return upKudo;
    }

    public void setUpKudo(Long upKudo) {
        this.upKudo = upKudo;
    }

    public Long getDownKudo() {
        return downKudo;
    }

    public void setDownKudo(Long downKudo) {
        this.downKudo = downKudo;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Short getIsShattered() {
        return isShattered;
    }

    public void setIsShattered(Short isShattered) {
        this.isShattered = isShattered;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public void setAnsweredBy(User answeredBy) {
        this.answeredBy = answeredBy;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public void setAnswerUserKudos(Set<AnswerUserKudo> answerUserKudos) {
        this.answerUserKudos = answerUserKudos;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", isImage=" + isImage +
                ", resourceLink='" + resourceLink + '\'' +
                ", caption='" + caption + '\'' +
                ", viewCount=" + viewCount +
                ", upKudo=" + upKudo +
                ", downKudo=" + downKudo +
                ", isDeleted=" + isDeleted +
                ", isShattered=" + isShattered +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", thumbnail='" + thumbnail + '\''+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Answer tmpAnswer= (Answer)obj;
        return id== tmpAnswer.id;
    }
}
