package com.platform.springsecurityjwt.rest;

import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.PeopleFollower;
import com.platform.springsecurityjwt.services.PeopleFollowerService;
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
@RequestMapping("/api/people")
public class PeopleFollowerRestController {
    @Autowired
    private PeopleFollowerService thePeopleFollowerService;

    @GetMapping("/followees/{followerid}/{currentPage}/{noOfElementsPerPage}")
    private Page<PeopleFollower> findByFollowerId(@PathVariable Long followerid, @PathVariable int currentPage, @PathVariable int noOfElementsPerPage){
        try{
            return thePeopleFollowerService.findByFollowerId(followerid,currentPage,noOfElementsPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/followers/{followeeId}/{currentPage}/{noOfElementsPerPage}")
    private Page<PeopleFollower> findByFolloweeId(@PathVariable Long followeeId, @PathVariable int currentPage, @PathVariable int noOfElementsPerPage){
        try{
            return thePeopleFollowerService.findByFolloweeId(followeeId,currentPage,noOfElementsPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
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
