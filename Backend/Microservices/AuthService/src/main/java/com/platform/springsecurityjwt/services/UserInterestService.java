package com.platform.springsecurityjwt.services;



import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.util.response.InterestReputationResponse;
import com.platform.springsecurityjwt.util.response.UserReputationResponse;

import java.util.List;

public interface UserInterestService {
    List<InterestReputationResponse> findByUserId(Long userId);
    List<UserReputationResponse> findByInterestId(Long interestId);
    void addUserInterest(UserInterest userInterest);
    void updateUserInterest(UserInterest userInterest);
    void deleteUserInterestById(Long userId,Long interestId);
}
