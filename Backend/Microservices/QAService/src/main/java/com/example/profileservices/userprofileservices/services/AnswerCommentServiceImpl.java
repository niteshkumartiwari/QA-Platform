package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.communication.UserService;
import com.example.profileservices.userprofileservices.dao.AnswerCommentDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.util.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService{
    private AnswerCommentDAO theAnswerCommentDAO;
    private AnswerService theAnswerService;
    private UserService theUserService;

    @Autowired
    public AnswerCommentServiceImpl(AnswerCommentDAO theAnswerCommentDAO,AnswerService theAnswerService,UserService theUserService) {
        this.theAnswerCommentDAO = theAnswerCommentDAO;
        this.theAnswerService = theAnswerService;
        this.theUserService= theUserService;
    }

    @Override
    public Page<AnswerComment> findByAnswerId(Long answerId, int currentPage, int noOfElemPerPage) {
        try{
            //return page sorted by createdOn field i.e. send recent comments first
            return theAnswerCommentDAO.findByAnswerId(answerId,
                    PageRequest.of(currentPage,noOfElemPerPage,Sort.by(Sort.Direction.DESC,"upkudo")
                            .and(Sort.by(Sort.Direction.DESC,"createdOn"))));
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public Page<AnswerComment> findByRepliedById(Long userId,int currentPage, int noOfElemPerPage) {
        try{
            return theAnswerCommentDAO.findByRepliedBy(userId,PageRequest.of(currentPage,noOfElemPerPage,Sort.by(Sort.Direction.DESC,"createdOn")));
        }
        catch (Exception e){
            throw new ApiRequestException("Something went Wrong.");
        }
    }

    @Override
    public void addComment(AnswerComment answerComment,String jwt) {
        Long answerId= answerComment.getAnsId();
        answerComment.setId(Long.valueOf(0));//to create new Object rather then updating

        try{
            User theUser= theUserService.getUser(answerComment.getRepliedBy().toString(),jwt);
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
