package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionUserKudoId;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionUserKudoDAO extends JpaRepository<QuestionUserKudo, QuestionUserKudoId> {
    Page<QuestionUserKudo> findByUser(Long userId, Pageable pageable);
    Page<QuestionUserKudo> findByQuestionId(Long questionId, Pageable pageable);
}
