package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.services.QuestionUserKudoService;
import com.example.profileservices.userprofileservices.util.retobjects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/kudos/questions")
public class QuestionUserKudoRestController {
    @Autowired
    private QuestionUserKudoService theQuestionUserKudoService;

    @GetMapping("/{id}")
    private UserKudoWrapper findByQuestionId(@PathVariable Long id){
        List<UserKudo> result=theQuestionUserKudoService.findByQuestionId(id);
        UserKudoWrapper userKudoWrapper = new UserKudoWrapper();
        userKudoWrapper.setUserKudo(result);
        return userKudoWrapper;
    }

    @GetMapping("/users/{id}")
    private QuestionKudoWrapper findByUserId(@PathVariable Long id){
        List<QuestionKudo> result= theQuestionUserKudoService.findByUserId(id);
        QuestionKudoWrapper questionKudoWrapper= new QuestionKudoWrapper();
        questionKudoWrapper.setQuestionKudos(result);
        return questionKudoWrapper;
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
