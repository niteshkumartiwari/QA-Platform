package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedUserDateResponse;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.models.QuestionSeen;
import com.example.profileservices.userprofileservices.services.QuestionSeenService;
import com.example.profileservices.userprofileservices.util.decorater.QuestionDateDecorator;
import com.example.profileservices.userprofileservices.util.decorater.UserDateDecorator;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seen/questions")
public class QuestionSeenRestController {
    @Autowired
    private QuestionSeenService theQuestionSeenService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}/{currentPage}/{noOfElemPerPage}")
    private UserDateDecorator findByQuestionId(@PathVariable Long id, @RequestHeader (name="Authorization") String jwt,
                                                                 @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<QuestionSeen> result;
        try{
            result=theQuestionSeenService.findByQuestionId(id,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        List<UserDateResponse> userAns= new ArrayList<>();
        for(QuestionSeen val: result.getContent()){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(val.getUser());
            tempUserDateResponse.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserDateResponse);
        }

        List<UserConvertedUserDateResponse> finalAns= theUserServiceCaller.addUserToUserDateResponse(userAns,jwt);
        UserDateDecorator decorator= UserDateDecorator.builder()
                .theUserConvertedUserDateResponses(finalAns)
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .sort(result.getSort())
                .size(result.getSize())
                .pageable(result.getPageable())
                .number(result.getNumber())
                .build();

        return decorator;
    }

    @GetMapping("/users/{id}/{currentPage}/{noOfElemPerPage}")
    private QuestionDateDecorator findByUserId(@PathVariable Long id, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<QuestionSeen> result;
        try{
            result= theQuestionSeenService.findByUserId(id,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
        List<QuestionDateResponse> question= new ArrayList<>();
        for(QuestionSeen questionSeen: result.getContent()){
            QuestionDateResponse tempAnswerDateResponse =new QuestionDateResponse();
            tempAnswerDateResponse.setQuestionId(questionSeen.getQuesId());
            tempAnswerDateResponse.setCreatedOn(questionSeen.getCreatedOn());

            question.add(tempAnswerDateResponse);
        }

        QuestionDateDecorator decorator= QuestionDateDecorator.builder()
                .theQuestionDateResponses(question)
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .sort(result.getSort())
                .size(result.getSize())
                .pageable(result.getPageable())
                .number(result.getNumber())
                .build();

        return decorator;
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
