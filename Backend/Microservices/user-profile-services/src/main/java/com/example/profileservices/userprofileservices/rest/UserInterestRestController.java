package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.UserInterest;
import com.example.profileservices.userprofileservices.services.UserInterestService;
import com.example.profileservices.userprofileservices.util.response.InterestReputationResponse;
import com.example.profileservices.userprofileservices.util.response.InterestReputationResponseWrapper;
import com.example.profileservices.userprofileservices.util.response.UserReputationResponse;
import com.example.profileservices.userprofileservices.util.response.UserReputationResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userId}/interests")
    private InterestReputationResponseWrapper findByUserId(@PathVariable Long userId){
        List<InterestReputationResponse> interestReputationResponses;
        try{
            interestReputationResponses= theUserInterestService.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        InterestReputationResponseWrapper interestReputationResponseWrapper= new InterestReputationResponseWrapper();
        interestReputationResponseWrapper.setInterestReputationResponseList(interestReputationResponses);

        return  interestReputationResponseWrapper;
    }

    @GetMapping("/interests/{interestId}")
    private UserReputationResponseWrapper findByInterestId(@PathVariable Long interestId){
        List<UserReputationResponse> userReputationResponses;
        try{
            userReputationResponses= theUserInterestService.findByInterestId(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        UserReputationResponseWrapper userReputationResponseWrapper= new UserReputationResponseWrapper();
        userReputationResponseWrapper.setUserReputationResponses(userReputationResponses);

        return userReputationResponseWrapper;
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
