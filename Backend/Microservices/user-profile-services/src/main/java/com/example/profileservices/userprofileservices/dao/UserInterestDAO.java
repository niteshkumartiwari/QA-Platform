package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.UserInterestId;
import com.example.profileservices.userprofileservices.models.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestDAO extends JpaRepository<UserInterest, UserInterestId> {
    List<UserInterest> findByUserId(Long userId);
    List<UserInterest> findByInterestId(Long interestId);
}
