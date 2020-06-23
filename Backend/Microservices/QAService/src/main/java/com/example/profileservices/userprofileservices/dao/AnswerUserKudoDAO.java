package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerUserKudoDAO extends JpaRepository<AnswerUserKudo, AnswerUserKudoId> {
    Page<AnswerUserKudo> findByUser(Long userId, Pageable pageable);
    Page<AnswerUserKudo> findByAnswerId(Long answerId, Pageable pageable);
}
