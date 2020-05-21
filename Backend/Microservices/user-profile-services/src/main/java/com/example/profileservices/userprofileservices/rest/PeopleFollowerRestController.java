package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.PeopleFollower;
import com.example.profileservices.userprofileservices.services.PeopleFollowerService;
import com.example.profileservices.userprofileservices.util.response.UserResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleFollowerRestController {
    @Autowired
    private PeopleFollowerService thePeopleFollowerService;

    @GetMapping("/followees/{followerid}")
    private UserResponseWrapper findByFollowerId(@PathVariable Long followerid){
        List<UserResponse> result;
        try{
            result=thePeopleFollowerService.findByFollowerId(followerid);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        UserResponseWrapper userResponseWrapper= new UserResponseWrapper();
        userResponseWrapper.setUserResponses(result);

        return userResponseWrapper;
    }

    @GetMapping("/followers/{followeeId}")
    private UserResponseWrapper findByFolloweeId(@PathVariable Long followeeId){
        List<UserResponse> result;
        try{
            result=thePeopleFollowerService.findByFolloweeId(followeeId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        UserResponseWrapper userResponseWrapper= new UserResponseWrapper();
        userResponseWrapper.setUserResponses(result);

        return userResponseWrapper;
    }

    @PostMapping()
    private ResponseEntity<String> addRelationShip(@RequestBody PeopleFollower thePeopleFollower){
        try{
            thePeopleFollowerService.addRelationShip(thePeopleFollower);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/followers/{followerId}/followees/{followeeId}")
    private ResponseEntity<String> deleteRelationShip(@PathVariable Long followerId,@PathVariable Long followeeId){
        try{
            thePeopleFollowerService.deleteRelationShip(followeeId,followerId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
