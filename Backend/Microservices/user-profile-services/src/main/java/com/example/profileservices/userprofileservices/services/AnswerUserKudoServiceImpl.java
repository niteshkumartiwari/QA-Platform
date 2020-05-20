package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerUserKudoDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;
import com.example.profileservices.userprofileservices.util.retobjects.AnswerKudo;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerUserKudoServiceImpl implements AnswerUserKudoService{
    private AnswerUserKudoDAO theAnswerUserKudoDAO;
    private AnswerService theAnswerService;
    private UserService theUserService;

    @Autowired
    public AnswerUserKudoServiceImpl(AnswerUserKudoDAO theAnswerUserKudoDAO, AnswerService theAnswerService, UserService theUserService) {
        this.theAnswerUserKudoDAO = theAnswerUserKudoDAO;
        this.theAnswerService = theAnswerService;
        this.theUserService = theUserService;
    }

    @Override
    public List<UserKudo> findByAnswerId(Long answerId) {
        List<AnswerUserKudo> result= theAnswerUserKudoDAO.findByAnswerId(answerId);
        List<UserKudo> userAns= new ArrayList<>();
        for(AnswerUserKudo val: result){
            UserKudo tempUserKudo = new UserKudo();
            tempUserKudo.setUserId(val.getUser().getId());
            tempUserKudo.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserKudo);
        }

        return userAns;
    }

    @Override
    public List<AnswerKudo> findByUserId(Long userId) {
        List<AnswerUserKudo> result= theAnswerUserKudoDAO.findByUserId(userId);
        List<AnswerKudo> answer= new ArrayList<>();
        for(AnswerUserKudo answerUserKudo: result){
            AnswerKudo tempAnswerKudo=new AnswerKudo();
            tempAnswerKudo.setAnswerId(answerUserKudo.getAnswer().getId());
            tempAnswerKudo.setCreatedOn(answerUserKudo.getCreatedOn());

            answer.add(tempAnswerKudo);
        }

        return answer;
    }

    @Override
    public void addKudo(AnswerUserKudo theAnswerUserKudo) {
        Long userId=theAnswerUserKudo.getUsrId();
        Long answerId=theAnswerUserKudo.getAnsId();
        AnswerUserKudoId answerUserKudoId= new AnswerUserKudoId(userId,answerId);
       if(theAnswerUserKudoDAO.findById(answerUserKudoId).isPresent()){
           throw new ApiRequestException("User has already Upkudo this answer");
        }

        try {
            theAnswerUserKudo.setAnswer(theAnswerService.findById(answerId));
            theAnswerUserKudo.setUser(theUserService.findById(userId));
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
