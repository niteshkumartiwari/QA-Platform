package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AnswerService {
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByUserId(Long userId);
    void create(Answer theAnswer);
    void update(Answer theAnswer);
    void deleteById(Long Id);
    Answer findByQuestionIdAndUserId(Long questionId, Long userId);
    Answer findById(Long id);
}
