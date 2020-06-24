package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerUserKudoDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerUserKudoServiceImpl implements AnswerUserKudoService{
    private AnswerUserKudoDAO theAnswerUserKudoDAO;
    private AnswerService theAnswerService;

    @Autowired
    public AnswerUserKudoServiceImpl(AnswerUserKudoDAO theAnswerUserKudoDAO, AnswerService theAnswerService) {
        this.theAnswerUserKudoDAO = theAnswerUserKudoDAO;
        this.theAnswerService = theAnswerService;
    }

    @Override
    public Page<AnswerUserKudo> findByAnswerId(Long answerId,int currentPage,int noOfElemPerPage) {
        Page<AnswerUserKudo> result= theAnswerUserKudoDAO.findByAnswerId(answerId, PageRequest.of(currentPage,noOfElemPerPage
                , Sort.by(Sort.Direction.DESC,"createdOn")));

        return result;
    }

    @Override
    public Page<AnswerUserKudo> findByUserId(Long userId, int currentPage, int noOfElemsPerPage) {
        Page<AnswerUserKudo> result= theAnswerUserKudoDAO.findByUser(userId,PageRequest.of(currentPage,noOfElemsPerPage
                , Sort.by(Sort.Direction.DESC,"createdOn")));

        return result;
    }

    @Override
    public void addKudo(AnswerUserKudo theAnswerUserKudo) {
        Long userId=theAnswerUserKudo.getUser();
        Long answerId=theAnswerUserKudo.getAnsId();
        AnswerUserKudoId answerUserKudoId= new AnswerUserKudoId(userId,answerId);
       if(theAnswerUserKudoDAO.findById(answerUserKudoId).isPresent()){
           throw new ApiRequestException("User has already Upkudo this answer");
        }

        try {
            theAnswerUserKudo.setAnswer(theAnswerService.findById(answerId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theAnswerUserKudo.setId(answerUserKudoId);
        theAnswerUserKudoDAO.save(theAnswerUserKudo);
    }

    @Override
    public void deleteKudo(Long answerId,Long userId) {
        AnswerUserKudoId theAnswerUserKudoId= new AnswerUserKudoId(userId,answerId);
        try{
            theAnswerUserKudoDAO.deleteById(theAnswerUserKudoId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userid and answerid.");
        }
    }
}
