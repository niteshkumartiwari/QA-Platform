package com.example.profileservices.userprofileservices.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.profileservices.userprofileservices.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;


public interface QuestionService {
	void create(Question theQuestion);
	void update(Question theQuestion);
	Page<Question> findAll(int currentPage,int noOfElemPerPage);
	Question findById(Long theId) throws Exception;
	void deleteById(Long theId);
	Page<Question> findByUserId(Long userId,int currentPage,int noOfElemPerPage);
}
