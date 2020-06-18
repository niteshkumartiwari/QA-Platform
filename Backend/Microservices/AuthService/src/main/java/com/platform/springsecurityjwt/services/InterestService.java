package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.Interest;

import java.util.List;
import java.util.Map;

public interface InterestService {
    List<Interest> findAllInterests();
    Map<Long,Interest> findAllInterestsByIds(List<Long> interestIds);
    Interest findById(Long interestId);
    void addInterest(Interest interest);
    void updateInterest(Interest interest);
    void deleteInterest(Long interestId);
}
