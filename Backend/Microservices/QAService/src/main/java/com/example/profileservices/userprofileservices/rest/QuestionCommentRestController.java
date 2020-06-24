package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestionComment;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.QuestionComment;
import com.example.profileservices.userprofileservices.services.QuestionCommentService;
import com.example.profileservices.userprofileservices.util.decorater.QuestionCommentDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionCommentRestController {
    @Autowired
    private QuestionCommentService theQuestionCommentService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{quesId}/comments/{currentPage}/{noOfElemPerPage}")
    private QuestionCommentDecorator findByQuestionId(@PathVariable Long quesId,@RequestHeader (name="Authorization") String jwt,
                                                                @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            Page<QuestionComment> result= theQuestionCommentService.findByQuestionId(quesId, currentPage,noOfElemPerPage);
            List<UserConvertedQuestionComment> tmpAns= theUserServiceCaller.addUserToQuestionComment(result.getContent(),jwt);

            QuestionCommentDecorator decorator= QuestionCommentDecorator.builder()
                    .theUserConvertedQuestionComments(tmpAns)
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

    @GetMapping("/comments/users/{userId}/{currentPage}/{noOfElemPerPage}")
    private Page<QuestionComment> findByRepliedBy(@PathVariable Long userId,@PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theQuestionCommentService.findByRepliedById(userId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/comments")
    private ResponseEntity<String> addComment(@RequestBody QuestionComment questionComment){
        try{
            theQuestionCommentService.addComment(questionComment);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @PutMapping("/comments")
    private ResponseEntity<String> updateComment(@RequestBody QuestionComment questionComment){
        try{
            theQuestionCommentService.updateComment(questionComment);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @DeleteMapping("/comments/{commentId}")
    private ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        try{
            theQuestionCommentService.deleteComment(commentId);
        }
        catch (Exception e){
            throw new ApiRequestException("Comment Id Doesn't Exists.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
