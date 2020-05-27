package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionSeenId;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSeenDAO extends JpaRepository<QuestionSeen, QuestionSeenId> {
    List<QuestionSeen> findByUser(Long userId);
    List<QuestionSeen> findByQuestionId(Long questionId);
}
