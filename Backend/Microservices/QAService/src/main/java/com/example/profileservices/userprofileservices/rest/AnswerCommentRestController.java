package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedAnswerComment;
import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.exception.ApiResourceNotFound;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.services.AnswerCommentService;
import com.example.profileservices.userprofileservices.util.decorater.AnswerCommentPageDecorater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerCommentRestController {
    @Autowired
    private AnswerCommentService theAnswerCommentService;

    @Autowired
    private UserServiceCaller theUserServiceCaller;

    @GetMapping("/{ansId}/comments/{currentPage}/{noOfElemPerPage}")
    private AnswerCommentPageDecorater findByAnswerId(@PathVariable Long ansId, @RequestHeader (name="Authorization") String jwt,
                                                            @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            Page<AnswerComment> result= theAnswerCommentService.findByAnswerId(ansId,currentPage,noOfElemPerPage);
            List<UserConvertedAnswerComment> ans=theUserServiceCaller.addUserToAnswerComment(result.getContent(),jwt);

            AnswerCommentPageDecorater decorater= AnswerCommentPageDecorater.builder()
                    .number(result.getNumber())
                    .pageable(result.getPageable())
                    .size(result.getSize())
                    .sort(result.getSort())
                    .theUserConvertedAnswerComments(ans)
                    .totalElements(result.getTotalElements())
                    .totalPages(result.getTotalPages())
                    .build();

            return decorater;
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    //UserId -> User Object Not Needed since userId itself is with client
    @GetMapping("/comments/users/{userId}/{currentPage}/{noOfElemPerPage}")
    private Page<AnswerComment> findByRepliedBy(@PathVariable Long userId,@RequestHeader (name="Authorization") String jwt,
                                                @PathVariable int currentPage, @PathVariable int noOfElemPerPage){
        try{
            return theAnswerCommentService.findByRepliedById(userId,currentPage,noOfElemPerPage);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/comments")
    private ResponseEntity<String> addComment(@RequestBody AnswerComment answerComment,@RequestHeader (name="Authorization") String jwt){
        try{
            theAnswerCommentService.addComment(answerComment,jwt);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }

    @PutMapping("/comments")
    private ResponseEntity<String> updateComment(@RequestBody AnswerComment answerComment){
        try{
            theAnswerCommentService.updateComment(answerComment);
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
            theAnswerCommentService.deleteComment(commentId);
        }
        catch (Exception e){
            throw new ApiRequestException("Comment Id Doesn't Exists.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("success");
    }
}
