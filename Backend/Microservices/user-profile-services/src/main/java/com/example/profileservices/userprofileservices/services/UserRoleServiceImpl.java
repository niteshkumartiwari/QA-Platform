package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.dao.UserRoleDAO;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Id.UserRoleId;
import com.example.profileservices.userprofileservices.models.UserRole;
import com.example.profileservices.userprofileservices.util.response.RoleResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    private UserRoleDAO theUserRoleDAO;
    private UserService theUserService;
    private RoleService theRoleService;

    @Autowired
    public UserRoleServiceImpl(UserRoleDAO theUserRoleDAO, UserService theUserService, RoleService theRoleService) {
        this.theUserRoleDAO = theUserRoleDAO;
        this.theUserService = theUserService;
        this.theRoleService = theRoleService;
    }

    @Override
    public List<UserResponse> findByRoleId(Long roleId) {
        List<UserRole> userRoles;
        try {
            userRoles = theUserRoleDAO.findByRoleId(roleId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<UserResponse> userResponses= new ArrayList<>();
        for(UserRole userRole: userRoles){
            UserResponse tmpUserResponse= new UserResponse();
            tmpUserResponse.setUserId(userRole.getUser().getId());

            userResponses.add(tmpUserResponse);
        }

        return userResponses;
    }

    @Override
    public List<RoleResponse> findByUserId(Long userId) {
        List<UserRole> userRoles;
        try {
            userRoles = theUserRoleDAO.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<RoleResponse> roleResponses= new ArrayList<>();
        for(UserRole userRole: userRoles){
            RoleResponse tmpRoleResponse= new RoleResponse();
            tmpRoleResponse.setRoleId(userRole.getRole().getId());

            roleResponses.add(tmpRoleResponse);
        }

        return roleResponses;
    }

    @Override
    public void addRoleToUser(UserRole theUserRole) {
        Long roleId= theUserRole.getRleId();
        Long userId= theUserRole.getUsrId();
        UserRoleId userRoleId= new UserRoleId(userId,roleId);

        if(theUserRoleDAO.findById(userRoleId).isPresent()){
            throw new ApiRequestException("Already Exists.");
        }
        theUserRole.setId(userRoleId);
        theUserRoleDAO.save(theUserRole);
    }

    @Override
    public void deleteRole(Long userId, Long roleId) {
        UserRoleId theUserRoleId= new UserRoleId(userId,roleId);

        try{
            theUserRoleDAO.deleteById(theUserRoleId);
        }
        catch (Exception e){
            throw new ApiRequestException("bad combination of userId and roleId");
        }
    }
}
