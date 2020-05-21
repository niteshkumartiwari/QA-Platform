package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.services.QuestionFollowerService;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followers/questions")
public class QuestionFollowerRestController {
    @Autowired
    private QuestionFollowerService theQuestionFollowerService;

    @GetMapping("/{id}")
    private UserResponseWrapper findByQuestionId(@PathVariable Long id){
        List<UserResponse> result;
        try{
            result=theQuestionFollowerService.findByQuestionId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        UserResponseWrapper userResponseWrapper = new UserResponseWrapper();
        userResponseWrapper.setUserResponses(result);
        return userResponseWrapper;
    }

    @GetMapping("/users/{id}")
    private QuestionResponseWrapper findByUserId(@PathVariable Long id){
        List<QuestionResponse> result;
        try{
            result= theQuestionFollowerService.findByUserId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        QuestionResponseWrapper questionResponseWrapper = new QuestionResponseWrapper();
        questionResponseWrapper.setQuestionResponses(result);
        return questionResponseWrapper;
    }

    @PostMapping()
    private ResponseEntity<String> addFollower(@RequestBody QuestionFollower thQuestionFollower){
        try{
            theQuestionFollowerService.addFollower(thQuestionFollower);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{questionId}/users/{userId}")
    private ResponseEntity<String> deleteFollower(@PathVariable Long questionId, @PathVariable Long userId){
        try{
            theQuestionFollowerService.deleteFollower(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

}
