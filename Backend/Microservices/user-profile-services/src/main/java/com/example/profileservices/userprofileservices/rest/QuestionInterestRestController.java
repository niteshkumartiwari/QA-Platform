package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionInterest;
import com.example.profileservices.userprofileservices.services.QuestionInterestService;
import com.example.profileservices.userprofileservices.util.response.InterestResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class QuestionInterestRestController {
    @Autowired
    private QuestionInterestService theQuestionInterestService;

    @GetMapping("/questions/{questionId}")
    private List<InterestResponse> findByQuestionId(@PathVariable Long questionId){
        try{
            return theQuestionInterestService.findByQuestionId(questionId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/{interestId}/questions")
    private QuestionResponseWrapper findByInterestId(@PathVariable Long interestId){
        List<QuestionResponse> questionResponses;

        try{
            questionResponses= theQuestionInterestService.findByInterestId(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        QuestionResponseWrapper questionResponseWrapper= new QuestionResponseWrapper();
        questionResponseWrapper.setQuestionResponses(questionResponses);

        return questionResponseWrapper;
    }

    @PostMapping("/questions")
    private ResponseEntity<String> addInterestToQuestion(@RequestBody QuestionInterest theQuestionInterest){
        try{
            theQuestionInterestService.addInterestToQuestion(theQuestionInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @DeleteMapping("/{interestId}/questions/{questionId}")
    private ResponseEntity<String> deleteInterestToQuestion(@PathVariable Long interestId,@PathVariable Long questionId){
        try{
            theQuestionInterestService.deleteInterestById(questionId,interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }
}
