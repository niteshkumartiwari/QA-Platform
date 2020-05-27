package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.util.response.UserResponse;

import java.util.List;

public interface PeopleFollowerService {
    List<UserResponse> findByFollowerId(Long followerId);
    List<UserResponse> findByFolloweeId(Long followeeId);
    void addRelationShip(PeopleFollower thePeopleFollower);
    void deleteRelationShip(Long followeeId,Long followerId);
}
