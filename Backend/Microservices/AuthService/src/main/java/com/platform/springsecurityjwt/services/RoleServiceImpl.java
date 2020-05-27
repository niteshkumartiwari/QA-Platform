package com.platform.springsecurityjwt.services;

import com.platform.springsecurityjwt.dao.RoleDAO;
import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDAO theRoleDAO;

    @Override
    public List<Role> findAllRoles() {
        try{
            return theRoleDAO.findAll();
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Role findById(Long roleId) {
        if(!theRoleDAO.findById(roleId).isPresent()){
            throw new ApiRequestException("RoleId Not Found.");
        }

        return theRoleDAO.findById(roleId).get();
    }

    @Override
    public void addRole(Role role) {
        role.setId(Long.valueOf(0));

        theRoleDAO.save(role);
    }

    @Override
    public void updateRole(Role role) {
        theRoleDAO.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        try{
            theRoleDAO.deleteById(roleId);
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid roleId.");
        }
    }
}
