package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionUserKudoDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.QuestionUserKudoId;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionUserKudoServiceImpl implements QuestionUserKudoService{
    private QuestionUserKudoDAO theQuestionUserKudoDAO;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionUserKudoServiceImpl(QuestionUserKudoDAO theQuestionUserKudoDAO, QuestionService theQuestionService) {
        this.theQuestionUserKudoDAO = theQuestionUserKudoDAO;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public Page<QuestionUserKudo> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage) {
        return theQuestionUserKudoDAO.findByQuestionId(questionId, PageRequest.of(currentPage,noOfElemPerPage,
                Sort.by(Sort.Direction.DESC,"createdOn")));
    }

    @Override
    public Page<QuestionUserKudo> findByUserId(Long userId, int currentPage, int noOfElemPerPage) {
        return theQuestionUserKudoDAO.findByUser(userId, PageRequest.of(currentPage,noOfElemPerPage,
                Sort.by(Sort.Direction.DESC,"createdOn")));
    }

    @Override
    public void addKudo(QuestionUserKudo theQuestionUserKudo) {
        Long userId=theQuestionUserKudo.getUser();
        Long questionId=theQuestionUserKudo.getQuesId();
        QuestionUserKudoId questionUserKudoId= new QuestionUserKudoId(userId,questionId);
        if(theQuestionUserKudoDAO.findById(questionUserKudoId).isPresent()){
            throw new ApiRequestException("User has already Upkudo this question");
        }

        try {
            theQuestionUserKudo.setQuestion(theQuestionService.findById(questionId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theQuestionUserKudo.setId(questionUserKudoId);
        theQuestionUserKudoDAO.save(theQuestionUserKudo);
    }

    @Override
    public void deleteKudo(Long questionId, Long userId) {
        QuestionUserKudoId theQuestionUserKudoId= new QuestionUserKudoId(userId,questionId);
        try{
            theQuestionUserKudoDAO.deleteById(theQuestionUserKudoId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userId and questionId.");
        }
    }
}
