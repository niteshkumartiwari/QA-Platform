package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionSeenService {
    Page<QuestionSeen> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage);
    Page<QuestionSeen> findByUserId(Long userId, int currentPage, int noOfElemPerPage);
    void addSeen(QuestionSeen theQuestionSeen);
    void deleteSeen(Long questionId,Long userId);
}
