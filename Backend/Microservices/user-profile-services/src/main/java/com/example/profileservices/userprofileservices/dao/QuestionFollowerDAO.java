package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionFollowerId;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionFollowerDAO extends JpaRepository<QuestionFollower, QuestionFollowerId> {
    List<QuestionFollower> findByUserId(Long userId);
    List<QuestionFollower> findByQuestionId(Long questionId);
}
