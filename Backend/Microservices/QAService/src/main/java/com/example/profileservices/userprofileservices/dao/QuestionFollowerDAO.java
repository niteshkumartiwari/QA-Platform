package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionFollowerId;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionFollowerDAO extends JpaRepository<QuestionFollower, QuestionFollowerId> {
    Page<QuestionFollower> findByUser(Long userId, Pageable pageable);
    Page<QuestionFollower> findByQuestionId(Long questionId, Pageable pageable);
}
