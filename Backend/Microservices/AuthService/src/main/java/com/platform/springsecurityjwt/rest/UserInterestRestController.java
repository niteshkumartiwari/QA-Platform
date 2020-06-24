package com.platform.springsecurityjwt.rest;


import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.UserInterest;
import com.platform.springsecurityjwt.services.UserInterestService;
import com.platform.springsecurityjwt.util.response.InterestReputationResponse;
import com.platform.springsecurityjwt.util.response.InterestReputationResponseWrapper;
import com.platform.springsecurityjwt.util.response.UserReputationResponse;
import com.platform.springsecurityjwt.util.response.UserReputationResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserInterestRestController {
    @Autowired
    private UserInterestService theUserInterestService;

    @GetMapping("/{userId}/interests/{currentPage}/{noOfElemPerPage}")
    private Page<UserInterest> findByUserId(@PathVariable Long userId, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theUserInterestService.findByUserId(userId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/interests/{interestId}/{currentPage}/{noOfElemPerPage}")
    private Page<UserInterest> findByInterestId(@PathVariable Long interestId, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theUserInterestService.findByInterestId(interestId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/interests")
    private ResponseEntity<String> addUserInterest(@RequestBody UserInterest theUserInterest){
        try{
            theUserInterestService.addUserInterest(theUserInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @PutMapping("/interests")
    private ResponseEntity<String> updateUserInterest(@RequestBody UserInterest theUserInterest){
        try{
            theUserInterestService.updateUserInterest(theUserInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{userId}/interests/{interestId}")
    private ResponseEntity<String> deleteUserInterest(@PathVariable Long userId,@PathVariable Long interestId){
        try {
            theUserInterestService.deleteUserInterestById(userId,interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
