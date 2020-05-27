package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;

import java.util.List;

public interface AnswerSeenService {
    List<UserDateResponse> findByAnswerId(Long answerId);
    List<AnswerDateResponse> findByUserId(Long userId);
    void addSeen(AnswerSeen theAnswerSeen);
    void deleteSeen(Long answerId,Long userId);
}
