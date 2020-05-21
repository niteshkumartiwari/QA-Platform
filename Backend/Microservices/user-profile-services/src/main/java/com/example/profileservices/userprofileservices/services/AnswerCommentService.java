package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerComment;

import java.util.List;

public interface AnswerCommentService {
    List<AnswerComment> findByAnswerId(Long answerId);
    List<AnswerComment> findByRepliedById(Long userId);
    void addComment(AnswerComment answerComment);
    void updateComment(AnswerComment answerComment);
    void deleteComment(Long commentId);
}
