package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedUserDateResponse;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.AnswerSeen;
import com.example.profileservices.userprofileservices.services.AnswerSeenService;
import com.example.profileservices.userprofileservices.util.decorater.AnswerDateDecorator;
import com.example.profileservices.userprofileservices.util.decorater.UserDateDecorator;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponse;
import com.example.profileservices.userprofileservices.util.response.AnswerDateResponseWrapper;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seen/answers")
public class AnswerSeenRestController {
    @Autowired
    private AnswerSeenService theAnswerSeenService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}/{currentPage}/{noOfElemPerPage}")
    private UserDateDecorator findByAnswerId(@PathVariable Long id,@RequestHeader (name="Authorization") String jwt,
                                                               @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<AnswerSeen> tmpAns;
        try{
            tmpAns=theAnswerSeenService.findByAnswerId(id,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        List<AnswerSeen> result=tmpAns.getContent();
        List<UserDateResponse> userAns= new ArrayList<>();
        for(AnswerSeen val: result){
            UserDateResponse tempUserDateResponse = new UserDateResponse();
            tempUserDateResponse.setUserId(val.getUser());
            tempUserDateResponse.setCreatedOn(val.getCreatedOn());

            userAns.add(tempUserDateResponse);
        }
        List<UserConvertedUserDateResponse> finalAns= theUserServiceCaller.addUserToUserDateResponse(userAns,jwt);

        UserDateDecorator decorator= UserDateDecorator.builder()
                .theUserConvertedUserDateResponses(finalAns)
                .number(tmpAns.getNumber())
                .pageable(tmpAns.getPageable())
                .size(tmpAns.getSize())
                .sort(tmpAns.getSort())
                .totalElements(tmpAns.getTotalElements())
                .totalPages(tmpAns.getTotalPages())
                .build();

        return decorator;
    }

    @GetMapping("/users/{id}/{currentPage}/{noOfElemPerPage}")
    private AnswerDateDecorator findByUserId(@PathVariable Long id, @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<AnswerSeen> result;
        try{
            result= theAnswerSeenService.findByUserId(id,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        List<AnswerDateResponse> answer= new ArrayList<>();
        for(AnswerSeen answerSeen: result.getContent()){
            AnswerDateResponse tempAnswerDateResponse =new AnswerDateResponse();
            tempAnswerDateResponse.setAnswerId(answerSeen.getAnswer().getId());
            tempAnswerDateResponse.setCreatedOn(answerSeen.getCreatedOn());

            answer.add(tempAnswerDateResponse);
        }

        AnswerDateDecorator decorator= AnswerDateDecorator.builder()
                .theAnswerDateResponses(answer)
                .number(result.getNumber())
                .pageable(result.getPageable())
                .size(result.getSize())
                .sort(result.getSort())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();
        return decorator;
    }

    @PostMapping()
    private ResponseEntity<String> addSeen(@RequestBody AnswerSeen theAnswerSeen){
        try{
            theAnswerSeenService.addSeen(theAnswerSeen);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{answerId}/users/{userId}")
    private ResponseEntity<String> deleteSeen(@PathVariable Long answerId, @PathVariable Long userId){
        try{
            theAnswerSeenService.deleteSeen(answerId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
