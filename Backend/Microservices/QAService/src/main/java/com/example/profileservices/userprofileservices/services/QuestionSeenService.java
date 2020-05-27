package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;

import java.util.List;

public interface QuestionSeenService {
    List<UserDateResponse> findByQuestionId(Long questionId);
    List<QuestionDateResponse> findByUserId(Long userId);
    void addSeen(QuestionSeen theQuestionSeen);
    void deleteSeen(Long questionId,Long userId);
}
