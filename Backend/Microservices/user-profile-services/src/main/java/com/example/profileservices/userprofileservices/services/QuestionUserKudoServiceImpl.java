package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionUserKudoDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;
import com.example.profileservices.userprofileservices.models.Id.QuestionUserKudoId;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.util.retobjects.AnswerKudo;
import com.example.profileservices.userprofileservices.util.retobjects.QuestionKudo;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionUserKudoServiceImpl implements QuestionUserKudoService{
    private QuestionUserKudoDAO theQuestionUserKudoDAO;
    private UserService theUserService;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionUserKudoServiceImpl(QuestionUserKudoDAO theQuestionUserKudoDAO, UserService theUserService, QuestionService theQuestionService) {
        this.theQuestionUserKudoDAO = theQuestionUserKudoDAO;
        this.theUserService = theUserService;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public List<UserKudo> findByQuestionId(Long questionId) {
        List<QuestionUserKudo> result= theQuestionUserKudoDAO.findByQuestionId(questionId);
        List<UserKudo> userQues= new ArrayList<>();

        for(QuestionUserKudo theQuestionUserKudo: result){
            UserKudo tempUserKudo= new UserKudo();
            tempUserKudo.setUserId(theQuestionUserKudo.getUser().getId());
            tempUserKudo.setCreatedOn(theQuestionUserKudo.getCreatedOn());

            userQues.add(tempUserKudo);
        }
        return userQues;
    }

    @Override
    public List<QuestionKudo> findByUserId(Long userId) {
        List<QuestionUserKudo> result= theQuestionUserKudoDAO.findByUserId(userId);
        List<QuestionKudo> question= new ArrayList<>();
        for(QuestionUserKudo answerUserKudo: result){
            QuestionKudo tempAnswerKudo=new QuestionKudo();
            tempAnswerKudo.setQuestionId(answerUserKudo.getQuestion().getId());
            tempAnswerKudo.setCreatedOn(answerUserKudo.getCreatedOn());

            question.add(tempAnswerKudo);
        }

        return question;
    }

    @Override
    public void addKudo(QuestionUserKudo theQuestionUserKudo) {
        Long userId=theQuestionUserKudo.getUsrId();
        Long questionId=theQuestionUserKudo.getQuesId();
        QuestionUserKudoId questionUserKudoId= new QuestionUserKudoId(userId,questionId);
        if(theQuestionUserKudoDAO.findById(questionUserKudoId).isPresent()){
            throw new ApiRequestException("User has already Upkudo this question");
        }

        try {
            theQuestionUserKudo.setQuestion(theQuestionService.findById(questionId));
            theQuestionUserKudo.setUser(theUserService.findById(userId));
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
