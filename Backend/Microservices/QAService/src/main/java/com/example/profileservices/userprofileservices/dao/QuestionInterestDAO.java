package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionInterestId;
import com.example.profileservices.userprofileservices.models.QuestionInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionInterestDAO extends JpaRepository<QuestionInterest, QuestionInterestId> {
    List<QuestionInterest> findByInterest(Long interestId);
    List<QuestionInterest> findByQuestionId(Long questionId);
}
