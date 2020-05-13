package com.example.profileservices.userprofileservices.dao;


import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class QuestionDAOCustomImpl implements QuestionDAOCustom {
	@PersistenceContext
    private EntityManager theEntityManager;
	
	@Override
	public void createQuestion(String title, String description, Long askedBy, Long editedBy, Short isImage,
			String resourceLink, Long upKudo, Long downKudo, Short isDeleted, Short isBanned,
			Date  lastModified, String thumbnail) {
		
			StoredProcedureQuery createQuestionProcedure =
		              theEntityManager.createNamedStoredProcedureQuery("insertQuestion")
		              .setParameter("title", title)
		              .setParameter("description", description)
		              .setParameter("asked_by", askedBy)
		              .setParameter("edited_by", editedBy)
		              .setParameter("is_image", isImage)
		              .setParameter("res_link", resourceLink)
		              .setParameter("upkudo", upKudo)
		              .setParameter("downkudo", downKudo)
		              .setParameter("is_deleted", isDeleted)
		              .setParameter("is_banned", isBanned)
		              .setParameter("thumbnail", thumbnail);
			createQuestionProcedure.execute();
	}

}
