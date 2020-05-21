package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.PeopleFollowerId;
import com.example.profileservices.userprofileservices.models.PeopleFollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleFollowerDAO extends JpaRepository<PeopleFollower, PeopleFollowerId> {
    List<PeopleFollower> findByFollowerId(Long followerId);
    List<PeopleFollower> findByFolloweeId(Long followeeId);
}
