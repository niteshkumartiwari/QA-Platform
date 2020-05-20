package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.util.retobjects.AnswerKudo;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudo;

import java.util.List;

public interface AnswerUserKudoService {
    List<UserKudo> findByAnswerId(Long answerId);
    List<AnswerKudo> findByUserId(Long userId);
    void addKudo(AnswerUserKudo theAnswerUserKudo);
    void deleteKudo(Long answerId,Long userId);
}
