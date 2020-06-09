package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.QuestionUserKudoId;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionUserKudoDAO extends JpaRepository<QuestionUserKudo, QuestionUserKudoId> {
    List<QuestionUserKudo> findByUser(Long userId);
    List<QuestionUserKudo> findByQuestionId(Long questionId);
}
