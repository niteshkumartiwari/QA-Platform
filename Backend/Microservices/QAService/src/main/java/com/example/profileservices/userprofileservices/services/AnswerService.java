package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AnswerService {
    Page<Answer> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage);
    Page<Answer> findByUserId(Long userId, int currentPage, int noOfElemPerPage);
    void create(Answer theAnswer);
    void update(Answer theAnswer);
    void deleteById(Long Id);
    Answer findByQuestionIdAndUserId(Long questionId, Long userId);
    Answer findById(Long id);
}
