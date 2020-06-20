package com.platform.springsecurityjwt.rest;

import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.UserRole;
import com.platform.springsecurityjwt.services.UserRoleService;
import com.platform.springsecurityjwt.util.response.RoleResponse;
import com.platform.springsecurityjwt.util.response.UserResponse;
import com.platform.springsecurityjwt.util.response.UserResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{roleId}/users/{currentPage}/{noOfElemPerPage}")
    private Page<UserRole> findByRoleId(@PathVariable Long roleId, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theUserRoleService.findByRoleId(roleId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
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
