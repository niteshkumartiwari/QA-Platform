package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.models.id.PeopleFollowerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleFollowerDAO extends JpaRepository<PeopleFollower, PeopleFollowerId> {
    List<PeopleFollower> findByFollowerId(Long followerId);
    List<PeopleFollower> findByFolloweeId(Long followeeId);
}
