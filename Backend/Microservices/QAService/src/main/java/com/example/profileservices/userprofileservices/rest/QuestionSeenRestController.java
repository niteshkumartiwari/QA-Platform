package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.services.QuestionSeenService;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seen/questions")
public class QuestionSeenRestController {
    @Autowired
    private QuestionSeenService theQuestionSeenService;

    @GetMapping("/{id}")
    private UserDateResponseWrapper findByQuestionId(@PathVariable Long id){
        List<UserDateResponse> result;
        try{
            result=theQuestionSeenService.findByQuestionId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        UserDateResponseWrapper userDateResponseWrapper = new UserDateResponseWrapper();
        userDateResponseWrapper.setUserDateResponse(result);
        return userDateResponseWrapper;
    }

    @GetMapping("/users/{id}")
    private QuestionDateResponseWrapper findByUserId(@PathVariable Long id){
        List<QuestionDateResponse> result;
        try{
            result= theQuestionSeenService.findByUserId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        QuestionDateResponseWrapper questionDateResponseWrapper = new QuestionDateResponseWrapper();
        questionDateResponseWrapper.setQuestionDateResponses(result);
        return questionDateResponseWrapper;
    }

    @PostMapping()
    private ResponseEntity<String> addSeen(@RequestBody QuestionSeen theQuestionSeen){
        try{
            theQuestionSeenService.addSeen(theQuestionSeen);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{questionId}/users/{userId}")
    private ResponseEntity<String> deleteSeen(@PathVariable Long questionId, @PathVariable Long userId){
        try{
            theQuestionSeenService.deleteSeen(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
