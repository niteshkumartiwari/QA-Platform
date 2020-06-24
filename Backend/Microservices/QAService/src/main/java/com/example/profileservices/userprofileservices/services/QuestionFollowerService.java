package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionFollowerService {
    Page<QuestionFollower> findByQuestionId(Long questionId, int currentPage, int noOfElemPerPage);
    Page<QuestionFollower>  findByUserId(Long userId, int currentPage, int noOfElemPerPage);
    void addFollower(QuestionFollower theQuestionFollower);
    void deleteFollower(Long questionId,Long userId);
}
