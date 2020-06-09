package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;

import java.util.List;

public interface AnswerUserKudoService {
    List<UserDateResponse> findByAnswerId(Long answerId);
    List<AnswerDateResponse> findByUserId(Long userId);
    void addKudo(AnswerUserKudo theAnswerUserKudo);
    void deleteKudo(Long answerId,Long userId);
}
