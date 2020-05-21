package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;

import java.util.List;

public interface QuestionUserKudoService {
    List<UserDateResponse> findByQuestionId(Long questionId);
    List<QuestionDateResponse> findByUserId(Long userId);
    void addKudo(QuestionUserKudo theQuestionUserKudo);
    void deleteKudo(Long questionId,Long userId);
}
