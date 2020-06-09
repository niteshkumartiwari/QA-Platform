package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.Interest;

import java.util.List;

public interface InterestService {
    List<Interest> findAllInterests();
    Interest findById(Long interestId);
    void addInterest(Interest interest);
    void updateInterest(Interest interest);
    void deleteInterest(Long interestId);
}
