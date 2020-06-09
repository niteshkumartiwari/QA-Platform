package com.example.profileservices.userprofileservices.services;


import com.example.profileservices.userprofileservices.dao.QuestionSeenDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionSeenId;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserDateResponse> findByQuestionId(Long questionId) {
        List<QuestionSeen> result;
        try{
            result = theQuestionSeenDAO.findByQuestionId(questionId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<UserDateResponse> userAns= new ArrayList<>();
        for(QuestionSeen val: result){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(val.getUser());
            tempUserDateResponse.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserDateResponse);
        }

        return userAns;
    }

    @Override
    public List<QuestionDateResponse> findByUserId(Long userId) {
        List<QuestionSeen> result;
        try{
            result= theQuestionSeenDAO.findByUser(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<QuestionDateResponse> question= new ArrayList<>();
        for(QuestionSeen questionSeen: result){
            QuestionDateResponse tempAnswerDateResponse =new QuestionDateResponse();
            tempAnswerDateResponse.setQuestionId(questionSeen.getQuestion().getId());
            tempAnswerDateResponse.setCreatedOn(questionSeen.getCreatedOn());

            question.add(tempAnswerDateResponse);
        }

        return question;
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
