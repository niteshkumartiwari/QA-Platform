package com.example.profileservices.userprofileservices.rest;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.exception.ApiResourceNotFound;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.services.AnswerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{ansId}/comments")
    private List<AnswerComment> findByAnswerid(@PathVariable Long ansId){
        try{
            return theAnswerCommentService.findByAnswerId(ansId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/comments/users/{userId}")
    private List<AnswerComment> findByRepliedBy(@PathVariable Long userId){
        try{
            return theAnswerCommentService.findByRepliedById(userId);
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("/comments")
    private ResponseEntity<String> addComment(@RequestBody AnswerComment answerComment){
        try{
            theAnswerCommentService.addComment(answerComment);
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
