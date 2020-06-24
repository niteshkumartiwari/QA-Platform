package com.platform.springsecurityjwt.services;



import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.util.response.InterestReputationResponse;
import com.platform.springsecurityjwt.util.response.UserReputationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInterestService {
    Page<UserInterest> findByUserId(Long userId, int currentPage, int noOfElemPerPage);
    Page<UserInterest> findByInterestId(Long interestId, int currentPage,int noOfElemPerPage);
    void addUserInterest(UserInterest userInterest);
    void updateUserInterest(UserInterest userInterest);
    void deleteUserInterestById(Long userId,Long interestId);
}
