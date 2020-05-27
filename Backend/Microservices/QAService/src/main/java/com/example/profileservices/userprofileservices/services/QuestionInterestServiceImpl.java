package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionInterestDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionInterestId;
import com.example.profileservices.userprofileservices.models.QuestionInterest;
import com.example.profileservices.userprofileservices.util.response.InterestResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionInterestServiceImpl implements QuestionInterestService{
    private QuestionInterestDAO theQuestionInterestDAO;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionInterestServiceImpl(QuestionInterestDAO theQuestionInterestDAO, QuestionService theQuestionService) {
        this.theQuestionInterestDAO = theQuestionInterestDAO;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public List<InterestResponse> findByQuestionId(Long questionId) {
        List<QuestionInterest> questionInterests;
        try {
            questionInterests = theQuestionInterestDAO.findByQuestionId(questionId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<InterestResponse> interestResponses= new ArrayList<>();
        for(QuestionInterest questionInterest: questionInterests){
            InterestResponse tmpInterestResponse= new InterestResponse();
            tmpInterestResponse.setInterestId(questionInterest.getInterest());

            interestResponses.add(tmpInterestResponse);
        }

        return interestResponses;
    }

    @Override
    public List<QuestionResponse> findByInterestId(Long interestId) {
        List<QuestionInterest> questionInterests;
        try {
            questionInterests = theQuestionInterestDAO.findByQuestionId(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<QuestionResponse> questionResponses= new ArrayList<>();
        for(QuestionInterest questionInterest: questionInterests){
            QuestionResponse tmpQuestionResponse= new QuestionResponse();
            tmpQuestionResponse.setQuestionId(questionInterest.getQuestion().getId());

            questionResponses.add(tmpQuestionResponse);
        }

        return questionResponses;
    }

    @Override
    public void addInterestToQuestion(QuestionInterest theQuestionInterest) {
        Long interestId= theQuestionInterest.getInterest();
        Long questionId= theQuestionInterest.getQuesId();
        QuestionInterestId questionInterestId= new QuestionInterestId(questionId,interestId);

        if(theQuestionInterestDAO.findById(questionInterestId).isPresent()){
            throw new ApiRequestException("Already Exists.");
        }
        theQuestionInterest.setId(questionInterestId);
        theQuestionInterestDAO.save(theQuestionInterest);
    }

    @Override
    public void deleteInterestById(Long questionId, Long interestId) {
        QuestionInterestId questionInterestId= new QuestionInterestId(questionId,interestId);

        try{
            theQuestionInterestDAO.deleteById(questionInterestId);
        }
        catch (Exception e){
            throw new ApiRequestException("bad combination of questionId and interestId");
        }
    }
}
