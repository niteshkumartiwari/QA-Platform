package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedUserDateResponse;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionUserKudo;
import com.example.profileservices.userprofileservices.services.QuestionUserKudoService;
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

@RestController()
@RequestMapping("/api/kudos/questions")
public class QuestionUserKudoRestController {
    @Autowired
    private QuestionUserKudoService theQuestionUserKudoService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}/{currentPage}/{noOfElemPerPage}")
    private UserDateDecorator findByQuestionId(@PathVariable Long id, @RequestHeader (name="Authorization") String jwt,
                                                                 @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<QuestionUserKudo> result=theQuestionUserKudoService.findByQuestionId(id,currentPage,noOfElemPerPage);

        List<UserDateResponse> userQues= new ArrayList<>();
        for(QuestionUserKudo theQuestionUserKudo: result.getContent()){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(theQuestionUserKudo.getUser());
            tempUserDateResponse.setCreatedOn(theQuestionUserKudo.getCreatedOn());

            userQues.add(tempUserDateResponse);
        }

        List<UserConvertedUserDateResponse> finalAns= theUserServiceCaller.addUserToUserDateResponse(userQues,jwt);

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
    private QuestionDateDecorator findByUserId(@PathVariable Long id,@PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<QuestionUserKudo> result= theQuestionUserKudoService.findByUserId(id,currentPage,noOfElemPerPage);

        List<QuestionDateResponse> question= new ArrayList<>();
        for(QuestionUserKudo answerUserKudo: result.getContent()){
            QuestionDateResponse tempAnswerKudo=new QuestionDateResponse();
            tempAnswerKudo.setQuestionId(answerUserKudo.getQuesId());
            tempAnswerKudo.setCreatedOn(answerUserKudo.getCreatedOn());

            question.add(tempAnswerKudo);
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
