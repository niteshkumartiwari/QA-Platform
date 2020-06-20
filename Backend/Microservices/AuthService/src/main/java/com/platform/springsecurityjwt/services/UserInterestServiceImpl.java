package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.dao.UserInterestDAO;
import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.models.id.UserInterestId;
import com.platform.springsecurityjwt.util.response.InterestReputationResponse;
import com.platform.springsecurityjwt.util.response.UserReputationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<UserInterest> findByUserId(Long userId, int currentPage, int noOfElemPerPage) {
        try{
            return theUserInterestDAO.findByUserId(userId, (Pageable) PageRequest.of(currentPage,noOfElemPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<UserInterest> findByInterestId(Long interestId, int currentPage, int noOfElemPerPage) {
        try{
            return theUserInterestDAO.findByInterestId(interestId,(Pageable) PageRequest.of(currentPage,noOfElemPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
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
