package com.platform.springsecurityjwt.rest;


import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.Interest;
import com.platform.springsecurityjwt.models.User;
import com.platform.springsecurityjwt.services.InterestService;
import com.platform.springsecurityjwt.util.response.InterestResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interests")
public class InterestRestController {
    @Autowired
    private InterestService theInterestService;

    @GetMapping()
    private InterestResponseWrapper findAllInterest(){
        InterestResponseWrapper interestResponseWrapper= new InterestResponseWrapper();
        try{
            List<Interest> result= theInterestService.findAllInterests();
            interestResponseWrapper.setInterests(result);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return interestResponseWrapper;
    }

    @GetMapping("/{interestId}")
    private Interest findByInterestId(@PathVariable Long interestId){
        try{
            return theInterestService.findById(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("list/{interestIds}")
    public Map<Long,Interest> getAllUsers(@PathVariable List<Long> interestIds){
        try{
            return theInterestService.findAllInterestsByIds(interestIds);
        }
        catch(Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping()
    private ResponseEntity<String> addInterest(@RequestBody Interest theInterest){
        try{
            theInterestService.addInterest(theInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @PutMapping()
    private  ResponseEntity<String> updateInterest(@RequestBody Interest theInterest){
        try{
            theInterestService.updateInterest(theInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{interestId}")
    private ResponseEntity<String> deleteInterest(@PathVariable Long interestId){
        try{
            theInterestService.deleteInterest(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
