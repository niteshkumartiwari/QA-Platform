package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.models.Id.AnswerSeenId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSeenDAO extends JpaRepository<AnswerSeen, AnswerSeenId> {
    Page<AnswerSeen> findByUser(Long userId, Pageable pageable);
    Page<AnswerSeen> findByAnswerId(Long answerId, Pageable pageable);
}
