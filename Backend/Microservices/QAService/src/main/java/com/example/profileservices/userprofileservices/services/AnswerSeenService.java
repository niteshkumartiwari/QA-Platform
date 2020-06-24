package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnswerSeenService {
    Page<AnswerSeen> findByAnswerId(Long answerId, int currentPage, int noOfElemPerPage);
    Page<AnswerSeen> findByUserId(Long userId,int currentPage, int noOfElemPerPage);
    void addSeen(AnswerSeen theAnswerSeen);
    void deleteSeen(Long answerId,Long userId);
}
