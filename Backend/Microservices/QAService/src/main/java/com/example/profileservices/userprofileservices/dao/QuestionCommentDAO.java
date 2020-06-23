package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCommentDAO extends JpaRepository<QuestionComment,Long> {
    Page<QuestionComment> findByQuestionId(Long questionId, Pageable pageable);
    Page<QuestionComment> findByRepliedBy(Long userId, Pageable pageable);
}
