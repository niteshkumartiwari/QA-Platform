package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.PeopleFollower;
import com.example.profileservices.userprofileservices.util.response.UserResponse;

import java.util.List;

public interface PeopleFollowerService {
    List<UserResponse> findByFollowerId(Long followerId);
    List<UserResponse> findByFolloweeId(Long followeeId);
    void addRelationShip(PeopleFollower thePeopleFollower);
    void deleteRelationShip(Long followeeId,Long followerId);
}
