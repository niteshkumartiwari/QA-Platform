package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Role;
import com.example.profileservices.userprofileservices.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/roles")
public class RoleRestController {
    @Autowired
    private RoleService theRoleService;

    @GetMapping()
    private List<Role> findAllRoles(){
        try{
            return theRoleService.findAllRoles();
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/{roleId}")
    private Role findByRoleId(@PathVariable Long roleId){
        try{
            return theRoleService.findById(roleId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping()
    private ResponseEntity<String> addRole(@RequestBody Role theRole){
        try{
            theRoleService.addRole(theRole);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @PutMapping()
    private  ResponseEntity<String> updateRole(@RequestBody Role theRole){
        try{
            theRoleService.updateRole(theRole);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{roleId}")
    private ResponseEntity<String> deleteRole(@PathVariable Long roleId){
        try{
            theRoleService.deleteRole(roleId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
