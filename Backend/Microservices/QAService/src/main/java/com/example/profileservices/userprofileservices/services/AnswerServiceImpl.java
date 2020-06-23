package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerDAO;
import com.example.profileservices.userprofileservices.dao.QuestionDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
    private AnswerDAO theAnswerDAO;
    private QuestionDAO theQuestionDAO;

    @Autowired
    public AnswerServiceImpl(AnswerDAO theAnswerDAO, QuestionDAO theQuestionDAO) {
        this.theAnswerDAO = theAnswerDAO;
        this.theQuestionDAO = theQuestionDAO;
    }

    @Override
    public Page<Answer> findByQuestionId(Long questionId,int currentPage, int noOfElemPerPage) {
        return theAnswerDAO.findByQuestionId(questionId, PageRequest.of(currentPage,noOfElemPerPage,
                Sort.by(Sort.Direction.DESC,"upKudo")));
    }

    @Override
    public Page<Answer> findByUserId(Long userId,int currentPage, int noOfElemPerPage) {
        return theAnswerDAO.findByAnsweredBy(userId,PageRequest.of(currentPage,noOfElemPerPage,Sort.by(Sort.Direction.DESC,"upKudo")));
    }

    @Override
    public void create(Answer theAnswer) {
        theAnswer.setId(Long.valueOf(0));
        theAnswer.setQuestion(theQuestionDAO.findById(theAnswer.getQuestId()).get());
        theAnswerDAO.save(theAnswer);
    }

    @Override
    public void update(Answer theAnswer) {
        theAnswer.setQuestion(theQuestionDAO.findById(theAnswer.getQuestId()).get());
            theAnswerDAO.save(theAnswer);
    }

    @Override
    public void deleteById(Long theId) {
        theAnswerDAO.deleteById(theId);
    }

    @Override
    public Answer findByQuestionIdAndUserId(Long questionId, Long userId) {
        return theAnswerDAO.findByQuestionIdAndAnsweredBy(questionId,userId);
    }

    @Override
    public Answer findById(Long id) {
        try{
            return theAnswerDAO.findById(id).get();
        }
        catch (Exception e){
            throw new ApiRequestException("AnswerId Not Found");
        }
    }
}
