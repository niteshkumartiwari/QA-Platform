package com.example.profileservices.userprofileservices.models;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PrePersist;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="question",uniqueConstraints = {
		@UniqueConstraint(columnNames = {"title"})
})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="insertQuestion",
			procedureName = "public.sp_insert_question",
			parameters = {
					@StoredProcedureParameter(name="title",mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="description",mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="asked_by",mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="edited_by",mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="is_image",mode = ParameterMode.IN, type = Short.class),
					@StoredProcedureParameter(name="res_link",mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="upkudo",mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="downkudo",mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="is_deleted",mode = ParameterMode.IN, type = Short.class),
					@StoredProcedureParameter(name="is_banned",mode = ParameterMode.IN, type = Short.class),
					@StoredProcedureParameter(name="thumbnail",mode = ParameterMode.IN, type = String.class)
			}
	),
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
	
	@NotBlank
	@Column(name= "asked_by")
	private Long askedBy;
	
	@Column(name= "thumbnail")
	private String thumbNail;	
	
	@Column(name= "edited_by")
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
	
	@Column(name= "last_modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;
	
	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
		lastModified= new Date();
	}
}













