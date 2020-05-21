package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.UserInterest;
import com.example.profileservices.userprofileservices.util.response.InterestReputationResponse;
import com.example.profileservices.userprofileservices.util.response.UserReputationResponse;

import java.util.List;

public interface UserInterestService {
    List<InterestReputationResponse> findByUserId(Long userId);
    List<UserReputationResponse> findByInterestId(Long interestId);
    void addUserInterest(UserInterest userInterest);
    void updateUserInterest(UserInterest userInterest);
    void deleteUserInterestById(Long userId,Long interestId);
}
