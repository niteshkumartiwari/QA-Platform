package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertUserResponse;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestion;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionFollower;
import com.example.profileservices.userprofileservices.services.QuestionFollowerService;
import com.example.profileservices.userprofileservices.services.QuestionService;
import com.example.profileservices.userprofileservices.util.response.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    private List<UserConvertUserResponse> findByQuestionId(@PathVariable Long id,@RequestHeader (name="Authorization") String jwt){
        List<UserResponse> result;
        try{
            result=theQuestionFollowerService.findByQuestionId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return theUserServiceCaller.addUserToUserResponse(result,jwt);
    }

    //Note: No need to convert the
    @GetMapping("/users/{id}")
    private List<UserConvertedQuestion> findByUserId(@PathVariable Long id, @RequestHeader (name="Authorization") String jwt) throws Exception{
        List<QuestionResponse> result;
        try{
            result= theQuestionFollowerService.findByUserId(id);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        List<Question> questionList = new ArrayList<>();
        for(QuestionResponse questionResponse: result){
            Question question=theQuestionService.findById(questionResponse.getQuestionId());
            questionList.add(question);
        }

        return theUserServiceCaller.addUserToQuestion(questionList,jwt);
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
