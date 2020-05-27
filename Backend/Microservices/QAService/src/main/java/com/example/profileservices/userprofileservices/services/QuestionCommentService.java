package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.models.QuestionComment;

import java.util.List;

public interface QuestionCommentService {
    List<QuestionComment> findByQuestionId(Long questionId);
    List<QuestionComment> findByRepliedById(Long userId);
    void addComment(QuestionComment questionComment);
    void updateComment(QuestionComment questionComment);
    void deleteComment(Long commentId);
}
