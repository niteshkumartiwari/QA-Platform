package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.util.retobjects.QuestionKudo;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudo;

import java.util.List;

public interface QuestionUserKudoService {
    List<UserKudo> findByQuestionId(Long questionId);
    List<QuestionKudo> findByUserId(Long userId);
    void addKudo(QuestionUserKudo theQuestionUserKudo);
    void deleteKudo(Long questionId,Long userId);
}
