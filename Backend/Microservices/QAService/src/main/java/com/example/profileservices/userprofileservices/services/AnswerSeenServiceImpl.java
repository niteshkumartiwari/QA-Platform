package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerSeenDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.models.Id.AnswerSeenId;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
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
public class AnswerSeenServiceImpl implements AnswerSeenService{
    private AnswerSeenDAO theAnswerSeenDAO;
    private AnswerService theAnswerService;

    @Autowired
    public AnswerSeenServiceImpl(AnswerSeenDAO theAnswerSeenDAO, AnswerService theAnswerService) {
        this.theAnswerSeenDAO = theAnswerSeenDAO;
        this.theAnswerService = theAnswerService;
    }

    @Override
    public Page<AnswerSeen> findByAnswerId(Long answerId, int currentPage, int noOfElemPerPage) {
        List<AnswerSeen> result;
        Page<AnswerSeen> tmpAns;
        try{
           return theAnswerSeenDAO.findByAnswerId(answerId, PageRequest.of(currentPage,noOfElemPerPage, Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<AnswerSeen> findByUserId(Long userId, int currentPage, int noOfElemPerPage) {
        Page<AnswerSeen> result;
        try{
            result= theAnswerSeenDAO.findByUser(userId,PageRequest.of(currentPage,noOfElemPerPage, Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return result;
    }

    @Override
    public void addSeen(AnswerSeen theAnswerSeen) {
        Long userId=theAnswerSeen.getUser();
        Long answerId=theAnswerSeen.getAnsId();
        AnswerSeenId answerSeenId= new AnswerSeenId(userId,answerId);
        if(theAnswerSeenDAO.findById(answerSeenId).isPresent()){
            throw new ApiRequestException("User has already seen this answer");
        }

        try {
            theAnswerSeen.setAnswer(theAnswerService.findById(answerId));
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
