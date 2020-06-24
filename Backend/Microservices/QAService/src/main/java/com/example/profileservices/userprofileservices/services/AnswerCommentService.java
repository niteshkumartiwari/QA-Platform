package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnswerCommentService {
    Page<AnswerComment> findByAnswerId(Long answerId, int currentPage, int noOfElemPerPage);
    Page<AnswerComment> findByRepliedById(Long userId, int currentPage, int noOfElemPerPage);
    void addComment(AnswerComment answerComment, String jwt);
    void updateComment(AnswerComment answerComment);
    void deleteComment(Long commentId);
}
