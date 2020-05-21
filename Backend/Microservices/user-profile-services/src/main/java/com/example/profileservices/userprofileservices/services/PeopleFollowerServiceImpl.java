package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.PeopleFollowerDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.PeopleFollowerId;
import com.example.profileservices.userprofileservices.models.PeopleFollower;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleFollowerServiceImpl implements PeopleFollowerService{
    private PeopleFollowerDAO thePeopleFollowerDAO;
    private UserService theUserService;

    @Autowired
    public PeopleFollowerServiceImpl(PeopleFollowerDAO thePeopleFollowerDAO, UserService theUserService) {
        this.thePeopleFollowerDAO = thePeopleFollowerDAO;
        this.theUserService = theUserService;
    }

    @Override
    public List<UserResponse> findByFollowerId(Long followerId) {
        List<PeopleFollower> result;
        try{
            result= thePeopleFollowerDAO.findByFollowerId(followerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid followerId");
        }
        List<UserResponse> userResponses=new ArrayList<>();
        for(PeopleFollower peopleFollower: result){
            UserResponse userResponse= new UserResponse();
            userResponse.setUserId(peopleFollower.getFollowee().getId());

            userResponses.add(userResponse);
        }

        return userResponses;
    }

    @Override
    public List<UserResponse> findByFolloweeId(Long followeeId) {
        List<PeopleFollower> result;
        try{
            result= thePeopleFollowerDAO.findByFolloweeId(followeeId);
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid followeeId");
        }
        List<UserResponse> userResponses=new ArrayList<>();
        for(PeopleFollower peopleFollower: result){
            UserResponse userResponse= new UserResponse();
            userResponse.setUserId(peopleFollower.getFollower().getId());

            userResponses.add(userResponse);
        }

        return userResponses;
    }

    @Override
    public void addRelationShip(PeopleFollower thePeopleFollower) {
        Long followerId= thePeopleFollower.getFollowTo();
        Long followeeId= thePeopleFollower.getFollowFrom();

        PeopleFollowerId peopleFollowerId= new PeopleFollowerId(followerId,followeeId);

        try{
            thePeopleFollower.setFollowee(theUserService.findById(followeeId));
            thePeopleFollower.setFollower(theUserService.findById(followerId));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        thePeopleFollower.setId(peopleFollowerId);
        thePeopleFollowerDAO.save(thePeopleFollower);
    }

    @Override
    public void deleteRelationShip(Long followeeId, Long followerId) {
        PeopleFollowerId peopleFollowerId= new PeopleFollowerId(followerId,followeeId);
        try{
            thePeopleFollowerDAO.deleteById(peopleFollowerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad followeeId and followerId combination");
        }
    }
}
