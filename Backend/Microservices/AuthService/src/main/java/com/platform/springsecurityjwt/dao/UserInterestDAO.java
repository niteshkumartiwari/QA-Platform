package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.models.id.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestDAO extends JpaRepository<UserInterest, UserInterestId> {
    List<UserInterest> findByUserId(Long userId);
    List<UserInterest> findByInterestId(Long interestId);
}
