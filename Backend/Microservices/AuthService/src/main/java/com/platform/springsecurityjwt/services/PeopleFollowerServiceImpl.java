package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.dao.PeopleFollowerDAO;
import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.models.id.PeopleFollowerId;
import com.platform.springsecurityjwt.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<PeopleFollower> findByFollowerId(Long followerId,int currentPage,int noOfElementsPerPage) {
        Page<PeopleFollower> result;
        try{
            result= thePeopleFollowerDAO.findByFollowerId(followerId, (Pageable) PageRequest.of(currentPage,noOfElementsPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid followerId");
        }

        return result;
    }

    @Override
    public Page<PeopleFollower> findByFolloweeId(Long followeeId, int currentPage, int noOfElementsPerPage) {
        Page<PeopleFollower> result;
        try{
            result= thePeopleFollowerDAO.findByFolloweeId(followeeId,(Pageable) PageRequest.of(currentPage,noOfElementsPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid followeeId");
        }

        return result;
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
