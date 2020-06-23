package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionUserKudoService {
    Page<QuestionUserKudo> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage);
    Page<QuestionUserKudo> findByUserId(Long userId, int currentPage, int noOfElemPerPage);
    void addKudo(QuestionUserKudo theQuestionUserKudo);
    void deleteKudo(Long questionId,Long userId);
}
