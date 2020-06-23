package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.QuestionCommentDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCommentServiceImpl implements QuestionCommentService{
    private QuestionCommentDAO theQuestionCommentDAO;
    private QuestionService theQuestionService;

    @Autowired
    public QuestionCommentServiceImpl(QuestionCommentDAO theQuestionCommentDAO, QuestionService theQuestionService) {
        this.theQuestionCommentDAO = theQuestionCommentDAO;
        this.theQuestionService = theQuestionService;
    }

    @Override
    public Page<QuestionComment> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionCommentDAO.findByQuestionId(questionId, PageRequest.of(currentPage,noOfElemPerPage,
                     Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public Page<QuestionComment> findByRepliedById(Long userId, int currentPage, int noOfElemPerPage) {
        try{
            return theQuestionCommentDAO.findByRepliedBy(userId,PageRequest.of(currentPage,noOfElemPerPage,
                    Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public void addComment(QuestionComment questionComment) {
        Long questionId= questionComment.getQuesId();
        questionComment.setId(Long.valueOf(0));

        try{
            questionComment.setQuestion(theQuestionService.findById(questionId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theQuestionCommentDAO.save(questionComment);
    }

    @Override
    public void updateComment(QuestionComment questionComment) {
        Long questionId= questionComment.getQuesId();

        if(!theQuestionCommentDAO.findById(questionComment.getId()).isPresent()){
            throw new ApiRequestException("Comment Id doesn't exist");
        }

        try{
            questionComment.setQuestion(theQuestionService.findById(questionId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        theQuestionCommentDAO.save(questionComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        try{
            theQuestionCommentDAO.deleteById(commentId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }
}
