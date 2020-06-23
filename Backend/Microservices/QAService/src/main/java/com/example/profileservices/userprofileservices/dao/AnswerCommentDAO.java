package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerCommentDAO extends JpaRepository<AnswerComment,Long> {
    Page<AnswerComment> findByAnswerId(Long answerId, Pageable pageable);
    Page<AnswerComment> findByRepliedBy(Long userId, Pageable pageable);
}
