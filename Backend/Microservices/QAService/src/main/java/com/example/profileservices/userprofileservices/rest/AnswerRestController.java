package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedAnswers;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Answer;
import com.example.profileservices.userprofileservices.services.AnswerService;
import com.example.profileservices.userprofileservices.util.decorater.AnswerPageDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerRestController {
    @Autowired
    private AnswerService theAnswerService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/questions/{questionId}/answers/{currentPage}/{noOfElemPerPage}")
    private AnswerPageDecorator findByQuestionId(@PathVariable Long questionId, @RequestHeader (name="Authorization") String jwt,
                                                        @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            Page<Answer> theAnswers= theAnswerService.findByQuestionId(questionId,currentPage,noOfElemPerPage);
            List<UserConvertedAnswers> answersWithUser =theUserServiceCaller.addUserToAnswer(theAnswers.getContent(),jwt);
            AnswerPageDecorator decorator= AnswerPageDecorator.builder()
                    .theUserConvertedAnswers(answersWithUser)
                    .number(theAnswers.getNumber())
                    .pageable(theAnswers.getPageable())
                    .size(theAnswers.getSize())
                    .sort(theAnswers.getSort())
                    .totalElements(theAnswers.getTotalElements())
                    .totalPages(theAnswers.getTotalPages())
                    .build();

            return decorator;
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @GetMapping("/users/{userId}/answers/{currentPage}/{noOfElemPerPage}")
    private Page<Answer> findByUserId(@PathVariable Long userId, @RequestHeader (name="Authorization") String jwt,
                                      @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theAnswerService.findByUserId(userId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @GetMapping("/users/{userId}/questions/{questionId}")
    private Answer findByUserIdAndQuestionId(@PathVariable Long questionId,@PathVariable Long userId){
        try{
            return theAnswerService.findByQuestionIdAndUserId(questionId,userId);
        }
        catch (Exception e){
            throw new ApiRequestException("Something Went Wrong!!");
        }
    }

    @PostMapping("/answers")
    private ResponseEntity<String> createAnswer(@RequestBody Answer theAnswer){
        try{
            theAnswerService.create(theAnswer);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Create Answer!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @PutMapping("/answers")
    private ResponseEntity<String> updateAnswer(@RequestBody Answer theAnswer){
        try{
            theAnswerService.update(theAnswer);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Update Answer!!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @DeleteMapping("/answers/{answerId}")
    private ResponseEntity<String> deleteAnswer(@PathVariable Long answerId){
        try{
            theAnswerService.deleteById(answerId);
        }
        catch (Exception e){
            throw new ApiRequestException("Cannot Delete Answer!!");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

}
