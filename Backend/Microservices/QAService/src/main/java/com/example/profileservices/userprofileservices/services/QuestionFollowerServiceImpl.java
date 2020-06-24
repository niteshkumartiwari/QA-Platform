package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionFollowerDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionFollowerId;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionFollowerServiceImpl implements QuestionFollowerService{
    private QuestionFollowerDAO theQuestionFollowerDAO;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionFollowerServiceImpl(QuestionFollowerDAO theQuestionFollowerDAO, QuestionService theQuestionService) {
        this.theQuestionFollowerDAO = theQuestionFollowerDAO;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public Page<QuestionFollower> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionFollowerDAO.findByQuestionId(questionId, (Pageable) PageRequest.of(currentPage,noOfElemPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<QuestionFollower> findByUserId(Long userId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionFollowerDAO.findByUser(userId, (Pageable) PageRequest.of(currentPage,noOfElemPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public void addFollower(QuestionFollower theQuestionFollower) {
        Long userId=theQuestionFollower.getUser();
        Long questionId=theQuestionFollower.getQuesId();
        QuestionFollowerId questionFollowerId= new QuestionFollowerId(userId,questionId);
        if(theQuestionFollowerDAO.findById(questionFollowerId).isPresent()){
            throw new ApiRequestException("User has already followed this question");
        }

        try {
            theQuestionFollower.setQuestion(theQuestionService.findById(questionId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theQuestionFollower.setId(questionFollowerId);
        theQuestionFollowerDAO.save(theQuestionFollower);
    }

    @Override
    public void deleteFollower(Long questionId, Long userId) {
        QuestionFollowerId questionFollowerId= new QuestionFollowerId(userId,questionId);
        try{
            theQuestionFollowerDAO.deleteById(questionFollowerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userid and questionId.");
        }
    }
}
