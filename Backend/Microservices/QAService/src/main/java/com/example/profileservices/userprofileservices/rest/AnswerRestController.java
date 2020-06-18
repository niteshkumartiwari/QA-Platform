package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedAnswers;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Answer;
import com.example.profileservices.userprofileservices.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerRestController {
    @Autowired
    private AnswerService theAnswerService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/questions/{questionId}/answers")
    private List<UserConvertedAnswers> findByQuestionId(@PathVariable Long questionId, @RequestHeader (name="Authorization") String jwt){
        try{
            List<Answer> theAnswers= theAnswerService.findByQuestionId(questionId);
            return theUserServiceCaller.addUserToAnswer(theAnswers,jwt);
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @GetMapping("/users/{userId}/answers")
    private List<Answer> findByUserId(@PathVariable Long userId){
        try{
            return theAnswerService.findByUserId(userId);
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @GetMapping("/users/{userId}/questions/{questionId}")
    private Answer findByUserIdAndQuestionId(@PathVariable Long questionId,@PathVariable Long userId){
        try{
            return theAnswerService.findByQuestionIdAndUserId(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @PostMapping("/answers")
    private ResponseEntity<String> createAnswer(@RequestBody Answer theAnswer){
        try{
            theAnswerService.create(theAnswer);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Create Answer!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @PutMapping("/answers")
    private ResponseEntity<String> updateAnswer(@RequestBody Answer theAnswer){
        try{
            theAnswerService.update(theAnswer);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Update Answer!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @DeleteMapping("/answers/{answerId}")
    private ResponseEntity<String> deleteAnswer(@PathVariable Long answerId){
        try{
            theAnswerService.deleteById(answerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Delete Answer!!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

}
