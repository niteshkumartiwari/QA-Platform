package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.util.response.QuestionDateResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;

import java.util.List;

public interface QuestionFollowerService {
    List<UserResponse> findByQuestionId(Long questionId);
    List<QuestionResponse> findByUserId(Long userId);
    void addFollower(QuestionFollower theQuestionFollower);
    void deleteFollower(Long questionId,Long userId);
}
