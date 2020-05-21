package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerCommentDAO extends JpaRepository<AnswerComment,Long> {
    List<AnswerComment> findByAnswerId(Long answerId);
    List<AnswerComment> findByRepliedById(Long userId);
}
