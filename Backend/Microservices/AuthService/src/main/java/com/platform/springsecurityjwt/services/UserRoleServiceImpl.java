package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.dao.UserRoleDAO;
import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.UserRole;
import com.platform.springsecurityjwt.models.id.UserRoleId;
import com.platform.springsecurityjwt.util.response.RoleResponse;
import com.platform.springsecurityjwt.util.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<UserRole> findByRoleId(Long roleId, int currentPage, int noOfElemPerPage) {
        try {
            return theUserRoleDAO.findByRoleId(roleId,(Pageable) PageRequest.of(currentPage,noOfElemPerPage));
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
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
