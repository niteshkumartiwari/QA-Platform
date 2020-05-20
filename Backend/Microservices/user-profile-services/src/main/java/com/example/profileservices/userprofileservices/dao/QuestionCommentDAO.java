package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCommentDAO extends JpaRepository<QuestionComment,Long> {
    List<QuestionComment> findByQuestionId(Long questionId);
    List<QuestionComment> findByRepliedById(Long userId);
}
