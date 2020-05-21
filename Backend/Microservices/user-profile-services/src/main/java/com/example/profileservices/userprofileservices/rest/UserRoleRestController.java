package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.UserRole;
import com.example.profileservices.userprofileservices.services.UserRoleService;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class UserRoleRestController {
    @Autowired
    private UserRoleService theUserRoleService;

    @GetMapping("/users/{userId}")
    private List<RoleResponse> findByUserId(@PathVariable Long userId){
        try{
            return theUserRoleService.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/{roleId}/users")
    private UserResponseWrapper findByRoleId(@PathVariable Long roleId){
        List<UserResponse> userResponses;

        try{
            userResponses= theUserRoleService.findByRoleId(roleId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        UserResponseWrapper userResponseWrapper= new UserResponseWrapper();
        userResponseWrapper.setUserResponses(userResponses);

        return userResponseWrapper;
    }

    @PostMapping("/users")
    private ResponseEntity<String> addRoleToUser(@RequestBody UserRole theUserRole){
        try{
            theUserRoleService.addRoleToUser(theUserRole);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @DeleteMapping("/{roleId}/users/{userId}")
    private ResponseEntity<String> deleteRelationship(@PathVariable Long roleId,@PathVariable Long userId){
        try{
            theUserRoleService.deleteRole(userId,roleId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }
}
