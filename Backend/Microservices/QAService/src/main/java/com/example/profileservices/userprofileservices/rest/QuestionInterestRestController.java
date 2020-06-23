package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestion;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionInterest;
import com.example.profileservices.userprofileservices.services.QuestionInterestService;
import com.example.profileservices.userprofileservices.services.QuestionService;
import com.example.profileservices.userprofileservices.util.decorater.QuestionDecorator;
import com.example.profileservices.userprofileservices.util.mapper.Interest;
import com.example.profileservices.userprofileservices.util.response.InterestResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponse;
import com.example.profileservices.userprofileservices.util.response.QuestionResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class QuestionInterestRestController {
    @Autowired
    private QuestionInterestService theQuestionInterestService;

    @Autowired
    private QuestionService theQuestionService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/questions/{questionId}")
    private List<Interest> findByQuestionId(@PathVariable Long questionId, @RequestHeader (name="Authorization") String jwt){
        try{
            List<InterestResponse> result= theQuestionInterestService.findByQuestionId(questionId);
            return  theUserServiceCaller.addInterestToInterestResponse(result,jwt);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/{interestId}/questions/{currentPage}/{noOfElemPerPage}")
    private QuestionDecorator findByInterestId(@PathVariable Long interestId, @RequestHeader (name="Authorization") String jwt,
                                                         @PathVariable int currentPage, @PathVariable int noOfElemPerPage)throws Exception{
        Page<QuestionInterest> result;

        try{
            result= theQuestionInterestService.findByInterestId(interestId,currentPage,noOfElemPerPage);

            List<Question> questionList = new ArrayList<>();
            for(QuestionInterest questionInterest: result){
                Question question=theQuestionService.findById(questionInterest.getQuesId());
                questionList.add(question);
            }

            List<UserConvertedQuestion> finalAns= theUserServiceCaller.addUserToQuestion(questionList,jwt);

            QuestionDecorator decorator= QuestionDecorator.builder()
                    .theUserConvertedQuestions(finalAns)
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

    @PostMapping("/questions")
    private ResponseEntity<String> addInterestToQuestion(@RequestBody QuestionInterest theQuestionInterest){
        try{
            theQuestionInterestService.addInterestToQuestion(theQuestionInterest);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }

    @DeleteMapping("/{interestId}/questions/{questionId}")
    private ResponseEntity<String> deleteInterestToQuestion(@PathVariable Long interestId,@PathVariable Long questionId){
        try{
            theQuestionInterestService.deleteInterestById(questionId,interestId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.TEXT_PLAIN)
                .body("success");
    }
}
