package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnswerUserKudoService {
    Page<AnswerUserKudo> findByAnswerId(Long answerId, int currentPage, int noOfElemsPerPage);
    Page<AnswerUserKudo> findByUserId(Long userId, int currentPage, int noOfElemsPerPage);
    void addKudo(AnswerUserKudo theAnswerUserKudo);
    void deleteKudo(Long answerId,Long userId);
}
