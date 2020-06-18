package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedUserDateResponse;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.services.QuestionUserKudoService;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/kudos/questions")
public class QuestionUserKudoRestController {
    @Autowired
    private QuestionUserKudoService theQuestionUserKudoService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}")
    private List<UserConvertedUserDateResponse> findByQuestionId(@PathVariable Long id, @RequestHeader (name="Authorization") String jwt){
        List<UserDateResponse> result=theQuestionUserKudoService.findByQuestionId(id);

        return theUserServiceCaller.addUserToUserDateResponse(result,jwt);
    }

    @GetMapping("/users/{id}")
    private QuestionDateResponseWrapper findByUserId(@PathVariable Long id){
        List<QuestionDateResponse> result= theQuestionUserKudoService.findByUserId(id);
        QuestionDateResponseWrapper questionDateResponseWrapper = new QuestionDateResponseWrapper();
        questionDateResponseWrapper.setQuestionDateResponses(result);
        return questionDateResponseWrapper;
    }

    @PostMapping()
    private ResponseEntity<String> addKudos(@RequestBody QuestionUserKudo theQuestionUserKudo){
        try{
            theQuestionUserKudoService.addKudo(theQuestionUserKudo);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{questionId}/users/{userId}")
    private ResponseEntity<String> deleteKudos(@PathVariable Long questionId, @PathVariable Long userId){
        try{
            theQuestionUserKudoService.deleteKudo(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
