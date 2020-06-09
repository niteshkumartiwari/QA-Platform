package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionFollowerDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionFollowerId;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserResponse> findByQuestionId(Long questionId) {
        List<QuestionFollower> result;
        try{
            result = theQuestionFollowerDAO.findByQuestionId(questionId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<UserResponse> userAns= new ArrayList<>();
        for(QuestionFollower val: result){
            UserResponse tempUserDateResponse = new UserResponse();
            tempUserDateResponse.setUserId(val.getUser());
            userAns.add(tempUserDateResponse);
        }

        return userAns;
    }

    @Override
    public List<QuestionResponse> findByUserId(Long userId) {
        List<QuestionFollower> result;
        try{
            result= theQuestionFollowerDAO.findByUser(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<QuestionResponse> question= new ArrayList<>();
        for(QuestionFollower questionSeen: result){
            QuestionResponse tempAnswerDateResponse =new QuestionResponse();
            tempAnswerDateResponse.setQuestionId(questionSeen.getQuestion().getId());
            question.add(tempAnswerDateResponse);
        }

        return question;
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
