package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.models.QuestionComment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionCommentService {
    Page<QuestionComment> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage);
    Page<QuestionComment> findByRepliedById(Long userId, int currentPage, int noOfElemPerPage);
    void addComment(QuestionComment questionComment);
    void updateComment(QuestionComment questionComment);
    void deleteComment(Long commentId);
}
