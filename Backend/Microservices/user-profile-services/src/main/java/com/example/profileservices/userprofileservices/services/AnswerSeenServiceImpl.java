package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerSeenDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.models.Id.AnswerSeenId;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerSeenServiceImpl implements AnswerSeenService{
    private AnswerSeenDAO theAnswerSeenDAO;
    private AnswerService theAnswerService;
    private UserService theUserService;

    @Autowired
    public AnswerSeenServiceImpl(AnswerSeenDAO theAnswerSeenDAO, AnswerService theAnswerService, UserService theUserService) {
        this.theAnswerSeenDAO = theAnswerSeenDAO;
        this.theAnswerService = theAnswerService;
        this.theUserService = theUserService;
    }

    @Override
    public List<UserDateResponse> findByAnswerId(Long answerId) {
        List<AnswerSeen> result;
        try{
           result = theAnswerSeenDAO.findByAnswerId(answerId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<UserDateResponse> userAns= new ArrayList<>();
        for(AnswerSeen val: result){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(val.getUser().getId());
            tempUserDateResponse.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserDateResponse);
        }

        return userAns;
    }

    @Override
    public List<AnswerDateResponse> findByUserId(Long userId) {
        List<AnswerSeen> result;
        try{
            result= theAnswerSeenDAO.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<AnswerDateResponse> answer= new ArrayList<>();
        for(AnswerSeen answerUserKudo: result){
            AnswerDateResponse tempAnswerDateResponse =new AnswerDateResponse();
            tempAnswerDateResponse.setAnswerId(answerUserKudo.getAnswer().getId());
            tempAnswerDateResponse.setCreatedOn(answerUserKudo.getCreatedOn());

            answer.add(tempAnswerDateResponse);
        }

        return answer;
    }

    @Override
    public void addSeen(AnswerSeen theAnswerSeen) {
        Long userId=theAnswerSeen.getUsrId();
        Long answerId=theAnswerSeen.getAnsId();
        AnswerSeenId answerSeenId= new AnswerSeenId(userId,answerId);
        if(theAnswerSeenDAO.findById(answerSeenId).isPresent()){
            throw new ApiRequestException("User has already seen this answer");
        }

        try {
            theAnswerSeen.setAnswer(theAnswerService.findById(answerId));
            theAnswerSeen.setUser(theUserService.findById(userId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theAnswerSeen.setId(answerSeenId);
        theAnswerSeenDAO.save(theAnswerSeen);
    }

    @Override
    public void deleteSeen(Long answerId, Long userId) {
        AnswerSeenId answerSeenId= new AnswerSeenId(userId,answerId);
        try{
            theAnswerSeenDAO.deleteById(answerSeenId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userid and answerid.");
        }
    }
}
