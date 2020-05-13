package com.example.profileservices.userprofileservices.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="user",schema = "public" ,uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames= {"email"})
})
public class User {
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
}
