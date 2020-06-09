package com.example.profileservices.userprofileservices.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.profileservices.userprofileservices.models.Question;
import org.springframework.web.bind.annotation.RequestBody;


public interface QuestionService {
	void create(Question theQuestion);
	void update(Question theQuestion);
	List<Question> findAll();
	Question findById(Long theId) throws Exception;
	void deleteById(Long theId);
	List<Question> findByUserId(Long userId);
}
