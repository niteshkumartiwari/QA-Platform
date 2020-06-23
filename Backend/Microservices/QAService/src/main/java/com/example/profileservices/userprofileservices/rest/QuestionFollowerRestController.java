package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertUserResponse;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestion;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.services.QuestionFollowerService;
import com.example.profileservices.userprofileservices.services.QuestionService;
import com.example.profileservices.userprofileservices.util.decorater.QuestionDecorator;
import com.example.profileservices.userprofileservices.util.decorater.UserResponseDecorator;
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
@RequestMapping("/api/followers/questions")
public class QuestionFollowerRestController {
    @Autowired
    private QuestionService theQuestionService;

    @Autowired
    private QuestionFollowerService theQuestionFollowerService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{id}/{currentPage}/{noOfElemPerPage}")
    private UserResponseDecorator findByQuestionId(@PathVariable Long id,@RequestHeader (name="Authorization") String jwt,
                                                            @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        Page<QuestionFollower> result;
        try{
            result=theQuestionFollowerService.findByQuestionId(id,currentPage,noOfElemPerPage);
            List<UserResponse> userAns= new ArrayList<>();
            for(QuestionFollower val: result.getContent()){
                UserResponse tempUserDateResponse = new UserResponse();
                tempUserDateResponse.setUserId(val.getUser());
                userAns.add(tempUserDateResponse);
            }

            List<UserConvertUserResponse> finalResponse= theUserServiceCaller.addUserToUserResponse(userAns,jwt);

            UserResponseDecorator decorator= UserResponseDecorator.builder()
                    .theUserConvertUserResponses(finalResponse)
                    .totalPages(result.getTotalPages())
                    .totalElements(result.getTotalElements())
                    .sort(result.getSort())
                    .size(result.getSize())
                    .pageable(result.getPageable())
                    .number(result.getNumber())
                    .build();

            return decorator;
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    //Note: No need to convert the user
    @GetMapping("/users/{id}/{currentPage}/{noOfElemPerPage}")
    private Page<QuestionFollower> findByUserId(@PathVariable Long id, @RequestHeader (name="Authorization") String jwt,
                                                @PathVariable int currentPage, @PathVariable int noOfElemPerPage) throws Exception{
        try{
            return theQuestionFollowerService.findByUserId(id,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping()
    private ResponseEntity<String> addFollower(@RequestBody QuestionFollower thQuestionFollower){
        try{
            theQuestionFollowerService.addFollower(thQuestionFollower);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/{questionId}/users/{userId}")
    private ResponseEntity<String> deleteFollower(@PathVariable Long questionId, @PathVariable Long userId){
        try{
            theQuestionFollowerService.deleteFollower(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

}
