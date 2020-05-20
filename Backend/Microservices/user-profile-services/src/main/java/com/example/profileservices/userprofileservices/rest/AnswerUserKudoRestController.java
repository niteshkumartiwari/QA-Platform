package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.services.AnswerUserKudoService;
import com.example.profileservices.userprofileservices.util.retobjects.AnswerKudo;
import com.example.profileservices.userprofileservices.util.retobjects.AnswerKudoWrapper;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudo;
import com.example.profileservices.userprofileservices.util.retobjects.UserKudoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/kudos")
public class AnswerUserKudoRestController {
    @Autowired
    private AnswerUserKudoService theAnswerUserKudoService;

    @GetMapping("/answers")
    private UserKudoWrapper findByAnswerId(@RequestBody Map< String, Long > values){
        Long id= values.get("ansId");
        List<UserKudo> result=theAnswerUserKudoService.findByAnswerId(id);
        UserKudoWrapper userKudoWrapper = new UserKudoWrapper();
        userKudoWrapper.setUserKudo(result);
        return userKudoWrapper;
    }

    @GetMapping("/users")
    private AnswerKudoWrapper findByUserId(@RequestBody Map< String, Long > values){
        Long id= values.get("usrId");
        List<AnswerKudo> result= theAnswerUserKudoService.findByUserId(id);
        AnswerKudoWrapper answerKudoWrapper= new AnswerKudoWrapper();
        answerKudoWrapper.setAnswerKudo(result);
        return answerKudoWrapper;
    }

    @PostMapping("/answers")
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

    @DeleteMapping("/answers")
    private ResponseEntity<String> deleteKudos(@RequestBody Map< String, Long > values){
        Long answerId= values.get("ansId");
        Long userId= values.get("usrId");
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
