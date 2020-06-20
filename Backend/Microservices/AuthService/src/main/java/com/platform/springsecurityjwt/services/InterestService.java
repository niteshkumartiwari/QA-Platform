package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.Interest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface InterestService {
    List<Interest> findAllInterests();
    Page<Interest> findAllInterestsByPages(int currentPage,int numberOfElementsPerPage,String sortParam);
    Map<Long,Interest> findAllInterestsByIds(List<Long> interestIds);
    Interest findById(Long interestId);
    void addInterest(Interest interest);
    void updateInterest(Interest interest);
    void deleteInterest(Long interestId);
}
