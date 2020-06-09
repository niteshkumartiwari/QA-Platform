package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerDAO;
import com.example.profileservices.userprofileservices.dao.QuestionDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Answer> findByQuestionId(Long questionId) {
        return theAnswerDAO.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> findByUserId(Long userId) {
        return theAnswerDAO.findByAnsweredBy(userId);
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
