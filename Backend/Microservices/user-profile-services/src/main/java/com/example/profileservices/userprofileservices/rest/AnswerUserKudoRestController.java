package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.services.AnswerUserKudoService;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponseWrapper;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kudos/answers")
public class AnswerUserKudoRestController {
    @Autowired
    private AnswerUserKudoService theAnswerUserKudoService;

    @GetMapping("/{id}")
    private UserDateResponseWrapper findByAnswerId(@PathVariable Long id){
        List<UserDateResponse> result=theAnswerUserKudoService.findByAnswerId(id);
        UserDateResponseWrapper userDateResponseWrapper = new UserDateResponseWrapper();
        userDateResponseWrapper.setUserDateResponse(result);
        return userDateResponseWrapper;
    }

    @GetMapping("/users/{id}")
    private AnswerDateResponseWrapper findByUserId(@PathVariable Long id){
        List<AnswerDateResponse> result= theAnswerUserKudoService.findByUserId(id);
        AnswerDateResponseWrapper answerDateResponseWrapper = new AnswerDateResponseWrapper();
        answerDateResponseWrapper.setAnswerDateResponse(result);
        return answerDateResponseWrapper;
    }

    @PostMapping()
    private ResponseEntity<String> addKudos(@RequestBody AnswerUserKudo theAnswerUserKudo){
        try{
            theAnswerUserKudoService.addKudo(theAnswerUserKudo);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{answerId}/users/{userId}")
    private ResponseEntity<String> deleteKudos(@PathVariable Long answerId, @PathVariable Long userId){
        try{
            theAnswerUserKudoService.deleteKudo(answerId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
