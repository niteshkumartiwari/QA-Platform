package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDAO extends JpaRepository<Answer,Long>{
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByAnsweredById(Long userId);
    Answer findByQuestionIdAndAnsweredById(Long questionId, Long userId);
}
