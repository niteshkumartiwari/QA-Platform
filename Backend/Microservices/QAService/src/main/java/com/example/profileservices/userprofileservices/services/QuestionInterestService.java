package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionInterest;
import com.example.profileservices.userprofileservices.util.response.InterestResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionInterestService {
    List<InterestResponse> findByQuestionId(Long questionId);
    Page<QuestionInterest> findByInterestId(Long interestId, int currentPage, int noOfElemPerPage);
    void addInterestToQuestion(QuestionInterest theQuestionInterest);
    void deleteInterestById(Long questionId,Long interestId);
}
