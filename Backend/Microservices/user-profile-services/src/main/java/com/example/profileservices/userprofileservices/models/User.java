package com.example.profileservices.userprofileservices.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user",schema = "public" ,uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames= {"email"})
})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Column(name = "username")
	private String username;
	
	@NotBlank
	@Column(name= "email")
	private String email;
	
	@NotBlank
	@Column(name= "password")
	private String password;
	
	@NotBlank
	@Column(name= "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name= "last_name")
	private String lastName;
	
	@Column(name= "bio")
	private String bio;
	
	@Column(name= "about_me")
	private String aboutMe;
	
	@Column(name= "profile_pic")
	private String profilePic;
	
	@Column(name= "reputations")
	private Long reputation;
	
	@Column(name= "is_deleted")
	private Integer isDeleted;
	
	@Column(name= "is_banned")
	private Integer isBanned;
	
	@Column(name= "created_on")
	private	Date createdOn;
	
	@Column(name= "last_login")
	private Date lastLogin;

	@OneToMany(mappedBy = "askedBy",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Question> questions;

	@OneToMany(mappedBy = "editedBy",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Question> editedQuestions;

	@OneToMany(mappedBy = "answeredBy",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Answer> answers;

	@OneToMany(mappedBy = "user")
	private Set<AnswerUserKudo> answerUserKudos;
	
	public User() {
	}

	public User(Long id, @NotBlank String username, @NotBlank String email, @NotBlank String password,
			@NotBlank String firstName, @NotBlank String lastName, String bio, String aboutMe, String profilePic,
			Long reputation, Integer isDeleted, Integer isBanned, Date createdOn, Date lastLogin) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.aboutMe = aboutMe;
		this.profilePic = profilePic;
		this.reputation = reputation;
		this.isDeleted = isDeleted;
		this.isBanned = isBanned;
		this.createdOn = createdOn;
		this.lastLogin = lastLogin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Long getReputation() {
		return reputation;
	}

	public void setReputation(Long reputation) {
		this.reputation = reputation;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(Integer isBanned) {
		this.isBanned = isBanned;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public void setAnswerUserKudos(Set<AnswerUserKudo> answerUserKudos) {
		this.answerUserKudos = answerUserKudos;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", bio='" + bio + '\'' +
				", aboutMe='" + aboutMe + '\'' +
				", profilePic='" + profilePic + '\'' +
				", reputation=" + reputation +
				", isDeleted=" + isDeleted +
				", isBanned=" + isBanned +
				", createdOn=" + createdOn +
				", lastLogin=" + lastLogin +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		User tmpUser= (User)obj;
		return id == tmpUser.id;
	}
}
