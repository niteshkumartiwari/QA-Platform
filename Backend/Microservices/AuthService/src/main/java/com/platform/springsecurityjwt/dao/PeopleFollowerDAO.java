package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.models.id.PeopleFollowerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PeopleFollowerDAO extends JpaRepository<PeopleFollower, PeopleFollowerId> {
    Page<PeopleFollower> findByFollowerId(Long followerId, Pageable pageable);
    Page<PeopleFollower> findByFolloweeId(Long followeeId, Pageable pageable);
}
