package com.example.profileservices.userprofileservices.services;


import com.example.profileservices.userprofileservices.dao.QuestionSeenDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionSeenId;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionSeenServiceImpl implements QuestionSeenService {
    private QuestionSeenDAO theQuestionSeenDAO;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionSeenServiceImpl(QuestionSeenDAO theQuestionSeenDAO, QuestionService theQuestionService) {
        this.theQuestionSeenDAO = theQuestionSeenDAO;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public Page<QuestionSeen> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionSeenDAO.findByQuestionId(questionId, PageRequest.of(currentPage,noOfElemPerPage
                    , Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<QuestionSeen> findByUserId(Long userId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionSeenDAO.findByUser(userId,PageRequest.of(currentPage,noOfElemPerPage,
                    Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public void addSeen(QuestionSeen theQuestionSeen) {
        Long userId=theQuestionSeen.getUser();
        Long questionId=theQuestionSeen.getQuesId();
        QuestionSeenId questionSeenId= new QuestionSeenId(userId,questionId);
        if(theQuestionSeenDAO.findById(questionSeenId).isPresent()){
            throw new ApiRequestException("User has already seen this question");
        }

        try {
            theQuestionSeen.setQuestion(theQuestionService.findById(questionId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theQuestionSeen.setId(questionSeenId);
        theQuestionSeenDAO.save(theQuestionSeen);
    }

    @Override
    public void deleteSeen(Long questionId, Long userId) {
        QuestionSeenId questionSeenId= new QuestionSeenId(userId,questionId);
        try{
            theQuestionSeenDAO.deleteById(questionSeenId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userid and questionId.");
        }
    }
}
