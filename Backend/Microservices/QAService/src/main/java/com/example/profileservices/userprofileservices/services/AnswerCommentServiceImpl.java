package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.AnswerCommentDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService{
    private AnswerCommentDAO theAnswerCommentDAO;
    private AnswerService theAnswerService;

    @Autowired
    public AnswerCommentServiceImpl(AnswerCommentDAO theAnswerCommentDAO,AnswerService theAnswerService) {
        this.theAnswerCommentDAO = theAnswerCommentDAO;
        this.theAnswerService = theAnswerService;
    }

    @Override
    public List<AnswerComment> findByAnswerId(Long answerId) {
        try{
            return theAnswerCommentDAO.findByAnswerId(answerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public List<AnswerComment> findByRepliedById(Long userId) {
        try{
            return theAnswerCommentDAO.findByRepliedBy(userId);
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public void addComment(AnswerComment answerComment) {
        Long answerId= answerComment.getAnsId();
        answerComment.setId(Long.valueOf(0));//to create new Object rather then updating

        try{
            answerComment.setAnswer(theAnswerService.findById(answerId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theAnswerCommentDAO.save(answerComment);
    }

    @Override
    public void updateComment(AnswerComment answerComment) {
        Long answerId= answerComment.getAnsId();

        if(!theAnswerCommentDAO.findById(answerComment.getId()).isPresent()){
            throw new ApiRequestException("Comment Id doesn't exist");
        }

        try{
            answerComment.setAnswer(theAnswerService.findById(answerId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theAnswerCommentDAO.save(answerComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        try{
            theAnswerCommentDAO.deleteById(commentId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
