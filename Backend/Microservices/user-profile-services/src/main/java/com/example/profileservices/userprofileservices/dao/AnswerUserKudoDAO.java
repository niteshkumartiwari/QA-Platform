package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.models.Id.AnswerUserKudoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerUserKudoDAO extends JpaRepository<AnswerUserKudo, AnswerUserKudoId> {
    List<AnswerUserKudo> findByUserId(Long userId);
    List<AnswerUserKudo> findByAnswerId(Long answerId);
}
