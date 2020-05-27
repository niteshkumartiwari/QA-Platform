package com.example.profileservices.userprofileservices.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="question",schema = "public",uniqueConstraints = {
		@UniqueConstraint(columnNames = {"title"})
})
public class Question implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Column(name = "title")
	private String title;
	
	@NotBlank
	@Column(name = "description")
	private String description;

	@Column(name = "asked_by")
	private Long askedBy;
	
	@Column(name= "thumbnail")
	private String thumbNail;

	@Column(name = "edited_by")
	private Long editedBy;
	
	@Column(name= "is_image")
	private Short isImage;
	
	@Column(name= "res_link")
	private String resourceLink;
	
	@Column(name= "upkudo")
	private Long upKudos;
	
	@Column(name= "downkudo")
	private Long downKudos;
	
	@Column(name= "is_deleted")
	private Short isDeleted;
	
	@Column(name= "is_banned")
	private Short isBanned;
	
	@Column(name= "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name= "last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Answer> answers;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<QuestionComment> comments;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<QuestionSeen> seens;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<QuestionFollower> questionFollowers;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<QuestionInterest> questionInterests;

	public Question() {
		this.isImage=0;
		this.upKudos=Long.valueOf(0);
		this.downKudos=Long.valueOf(0);
		this.isDeleted=0;
		this.isBanned=0;
		this.createdOn=new Date(System.currentTimeMillis());
		this.lastModified=new Date(System.currentTimeMillis());
	}

	public Question(Long id, @NotBlank String title, @NotBlank String description, Long askedBy,
					String thumbNail, Long editedBy, Short isImage, String resourceLink, Long upKudos,
					Long downKudos, Short isDeleted, Short isBanned, Date createdOn, Date lastModified) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.askedBy = askedBy;
		this.thumbNail = thumbNail;
		this.editedBy = editedBy;
		this.isImage = isImage;
		this.resourceLink = resourceLink;
		this.upKudos = upKudos;
		this.downKudos = downKudos;
		this.isDeleted = isDeleted;
		this.isBanned = isBanned;
		this.createdOn = createdOn;
		this.lastModified = lastModified;
	}

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
		lastModified= new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbNail() {
		return thumbNail;
	}

	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
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

	public Long getUpKudos() {
		return upKudos;
	}

	public void setUpKudos(Long upKudos) {
		this.upKudos = upKudos;
	}

	public Long getDownKudos() {
		return downKudos;
	}

	public void setDownKudos(Long downKudos) {
		this.downKudos = downKudos;
	}

	public Short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Short isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Short getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Short isBanned) {
		this.isBanned = isBanned;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Long getAskedBy() {
		return askedBy;
	}

	public void setAskedBy(Long askedBy) {
		this.askedBy = askedBy;
	}

	public void setEditedBy(Long editedBy) {
		this.editedBy = editedBy;
	}

	public Long getEditedBy() {
		return editedBy;
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", thumbNail='" + thumbNail + '\'' +
				", isImage=" + isImage +
				", resourceLink='" + resourceLink + '\'' +
				", upKudos=" + upKudos +
				", downKudos=" + downKudos +
				", isDeleted=" + isDeleted +
				", isBanned=" + isBanned +
				", createdOn=" + createdOn +
				", lastModified=" + lastModified +
				'}';
	}
}













