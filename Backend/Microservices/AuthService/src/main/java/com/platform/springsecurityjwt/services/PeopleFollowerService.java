package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.util.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PeopleFollowerService {
    Page<PeopleFollower> findByFollowerId(Long followerId, int currentPage, int noOfElementsPerPage);
    Page<PeopleFollower> findByFolloweeId(Long followeeId, int currentPage, int noOfElementsPerPage);
    void addRelationShip(PeopleFollower thePeopleFollower);
    void deleteRelationShip(Long followeeId,Long followerId);
}
