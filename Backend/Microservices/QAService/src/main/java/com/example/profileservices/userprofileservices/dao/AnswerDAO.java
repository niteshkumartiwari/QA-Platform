package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDAO extends JpaRepository<Answer,Long>{
    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
    Page<Answer> findByAnsweredBy(Long userId, Pageable pageable);
    Answer findByQuestionIdAndAnsweredBy(Long questionId, Long userId);
}
