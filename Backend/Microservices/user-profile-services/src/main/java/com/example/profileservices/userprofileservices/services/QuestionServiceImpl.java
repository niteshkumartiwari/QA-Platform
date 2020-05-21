package com.example.profileservices.userprofileservices.services;

import java.util.List;
import java.util.Optional;

import com.example.profileservices.userprofileservices.dao.UserDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.exception.ApiResourceNotFound;
import com.example.profileservices.userprofileservices.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profileservices.userprofileservices.dao.QuestionDAO;
@Service
public class QuestionServiceImpl implements QuestionService{
	private QuestionDAO questionDAO;
	private UserDAO userDAO;

	@Autowired
	public QuestionServiceImpl(QuestionDAO questionDAO, UserDAO userDAO) {
		this.questionDAO = questionDAO;
		this.userDAO = userDAO;
	}

	@Override
	public void create(Question theQuestion){
		theQuestion.setId(Long.valueOf(0));
		theQuestion.setAskedBy(userDAO.findById(theQuestion.getAskedUid()).get());
		if(theQuestion.getEditedUid()!=null)
			theQuestion.setEditedBy(userDAO.findById(theQuestion.getEditedUid()).get());
		questionDAO.save(theQuestion);
	}

	@Override
	public void update(Question theQuestion) {
		theQuestion.setAskedBy(userDAO.findById(theQuestion.getAskedUid()).get());
		if(theQuestion.getEditedUid()!=null)
			theQuestion.setEditedBy(userDAO.findById(theQuestion.getEditedUid()).get());
		questionDAO.save(theQuestion);
	}

	@Override
	public List<Question> findAll() {
		return questionDAO.findAll();
	}

	@Override
	public Question findById(Long theId) throws Exception {
		Optional<Question> result= questionDAO.findById(theId);

		Question theQuestion=null;
		if(result.isPresent()){
			theQuestion= result.get();
		}
		else{
			throw new ApiResourceNotFound("Question Id Not Found");
		}

		return  theQuestion;
	}

	@Override
	public void deleteById(Long theId) {
		questionDAO.deleteById(theId);
	}

	@Override
	public List<Question> findByUserId(Long userId) {
		return questionDAO.findByAskedById(userId);
	}
}
