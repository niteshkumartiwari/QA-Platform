package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedUserDateResponse;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerUserKudo;
import com.example.profileservices.userprofileservices.services.AnswerUserKudoService;
import com.example.profileservices.userprofileservices.util.decorater.AnswerDateDecorator;
import com.example.profileservices.userprofileservices.util.decorater.UserDateDecorator;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponseWrapper;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import com.example.profileservices.userprofileservices.util.response.UserDateResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/kudos/answers")
public class AnswerUserKudoRestController {
    @Autowired
    private AnswerUserKudoService theAnswerUserKudoService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}/{currentPage}/{noOfElemPerPage}")
    private UserDateDecorator findByAnswerId(@PathVariable Long id,@RequestHeader (name="Authorization") String jwt,
                                                               @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<AnswerUserKudo> result=theAnswerUserKudoService.findByAnswerId(id,currentPage,noOfElemPerPage);

        List<UserDateResponse> userAns= new ArrayList<>();
        for(AnswerUserKudo val: result.getContent()){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(val.getUser());
            tempUserDateResponse.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserDateResponse);
        }

        List<UserConvertedUserDateResponse> finalResponse= theUserServiceCaller.addUserToUserDateResponse(userAns,jwt);

        UserDateDecorator decorator = UserDateDecorator.builder()
                .theUserConvertedUserDateResponses(finalResponse)
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
    private AnswerDateDecorator findByUserId(@PathVariable Long id, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<AnswerUserKudo> result= theAnswerUserKudoService.findByUserId(id,currentPage,noOfElemPerPage);

        List<AnswerDateResponse> answer= new ArrayList<>();
        for(AnswerUserKudo answerUserKudo: result.getContent()){
            AnswerDateResponse tempAnswerDateResponse =new AnswerDateResponse();
            tempAnswerDateResponse.setAnswerId(answerUserKudo.getAnswer().getId());
            tempAnswerDateResponse.setCreatedOn(answerUserKudo.getCreatedOn());

            answer.add(tempAnswerDateResponse);
        }

        AnswerDateDecorator decorator= AnswerDateDecorator.builder()
                .theAnswerDateResponses(answer)
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
