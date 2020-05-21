package com.example.profileservices.userprofileservices.rest;

import java.util.List;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
import com.example.profileservices.userprofileservices.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.profileservices.userprofileservices.services.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionRestController {
	@Autowired
	private QuestionService theQuestionService;

	//expose "/questions" and return list of questions
	@GetMapping("/questions")
	public List<Question> findAll(){
		return theQuestionService.findAll();
	}

	//expose "/questions/{questionId}" to get single question
	@GetMapping("/questions/{questionId}")
	public Question findById(@PathVariable Long questionId){
		Question theQuestion=null;
		try{
			theQuestion= theQuestionService.findById(questionId);
		}
		catch(Exception e){
			throw new ApiRequestException("Id Not Found");
		}

		return theQuestion;
	}

	//expose "/user/{userId}/questions" and return list of questions asked by userId
	@GetMapping("/users/{userId}/questions")
	public List<Question> findByUserId(@PathVariable Long userId){
		try{
			return theQuestionService.findByUserId(userId);
		}
		catch (Exception e){
			throw new ApiRequestException("Sorry, Some Error Happened");
		}
	}

	//add Mapping for POST /questions -add new question
	@PostMapping("/questions")
	public ResponseEntity<String> create(@RequestBody Question theQuestion) {

			try{
				theQuestionService.create(theQuestion);
			}
			catch (Exception e){
				throw new ApiRequestException("Oops! Cannot create question.");
			}

		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.TEXT_PLAIN)
				.body("success");
	}

	//add Mapping for PUT /questions -update existing question
	@PutMapping("/questions")
	public ResponseEntity<String> update(@RequestBody Question theQuestion){

		try{
			theQuestionService.update(theQuestion);
		}
		catch (Exception e){
			throw new ApiRequestException("Oops! Cannot update question.");
		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.TEXT_PLAIN)
				.body("success");
	}

	//add Mapping for DELETE /questions/{questionId}
	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId){
		Question theQuestion= null;
		try{
			theQuestion= theQuestionService.findById(questionId);
			theQuestionService.deleteById(questionId);
		}
		catch (Exception e){
			throw new ApiRequestException("Id Doesn't Exists.");
		}
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("success");
	}
}
