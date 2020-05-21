package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.UserInterestDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.UserInterestId;
import com.example.profileservices.userprofileservices.models.UserInterest;
import com.example.profileservices.userprofileservices.util.response.InterestReputationResponse;
import com.example.profileservices.userprofileservices.util.response.UserReputationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInterestServiceImpl implements UserInterestService{
    private UserInterestDAO theUserInterestDAO;
    private UserService theUserService;
    private InterestService theInterestService;

    @Autowired
    public UserInterestServiceImpl(UserInterestDAO theUserInterestDAO, UserService theUserService, InterestService theInterestService) {
        this.theUserInterestDAO = theUserInterestDAO;
        this.theUserService = theUserService;
        this.theInterestService = theInterestService;
    }

    @Override
    public List<InterestReputationResponse> findByUserId(Long userId) {
        List<UserInterest> userInterests;

        try{
            userInterests=theUserInterestDAO.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<InterestReputationResponse> interestReputationResponses= new ArrayList<>();
        for(UserInterest userInterest: userInterests){
            InterestReputationResponse tmpInterestReputationResponse= new InterestReputationResponse();
            tmpInterestReputationResponse.setInterestId(userInterest.getInterest().getId());
            tmpInterestReputationResponse.setReputation(userInterest.getReputation());

            interestReputationResponses.add(tmpInterestReputationResponse);
        }

        return interestReputationResponses;
    }

    @Override
    public List<UserReputationResponse> findByInterestId(Long interestId) {
        List<UserInterest> userInterests;

        try{
            userInterests=theUserInterestDAO.findByInterestId(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<UserReputationResponse> userReputationResponses= new ArrayList<>();
        for(UserInterest userInterest: userInterests){
            UserReputationResponse tmpUserReputationResponse= new UserReputationResponse();
            tmpUserReputationResponse.setUserId(userInterest.getUser().getId());
            tmpUserReputationResponse.setReputation(userInterest.getReputation());

            userReputationResponses.add(tmpUserReputationResponse);
        }

        return userReputationResponses;
    }

    @Override
    public void addUserInterest(UserInterest userInterest) {
        Long userId= userInterest.getUsrId();
        Long interestId= userInterest.getIntrestId();
        UserInterestId userInterestId= new UserInterestId(userId,interestId);
        try{
            userInterest.setUser(theUserService.findById(userId));
            userInterest.setInterest(theInterestService.findById(interestId));
            userInterest.setId(userInterestId);
            theUserInterestDAO.save(userInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public void updateUserInterest(UserInterest userInterest) {
        addUserInterest(userInterest);
    }

    @Override
    public void deleteUserInterestById(Long userId, Long interestId) {
        UserInterestId userInterestId= new UserInterestId(userId,interestId);
        try{
            theUserInterestDAO.deleteById(userInterestId);
        }
        catch (Exception e){
            throw new ApiRequestException("Bad Combination of userId and interestId");
        }
    }
}
