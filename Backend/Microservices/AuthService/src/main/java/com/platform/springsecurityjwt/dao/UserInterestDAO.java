package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.models.id.UserInterestId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestDAO extends JpaRepository<UserInterest, UserInterestId> {
    Page<UserInterest> findByUserId(Long userId, Pageable pageable);
    Page<UserInterest> findByInterestId(Long interestId, Pageable pageable);
}
