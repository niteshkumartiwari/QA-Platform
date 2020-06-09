package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionInterest;
import com.example.profileservices.userprofileservices.util.response.InterestResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;

import java.util.List;

public interface QuestionInterestService {
    List<InterestResponse> findByQuestionId(Long questionId);
    List<QuestionResponse> findByInterestId(Long interestId);
    void addInterestToQuestion(QuestionInterest theQuestionInterest);
    void deleteInterestById(Long questionId,Long interestId);
}
