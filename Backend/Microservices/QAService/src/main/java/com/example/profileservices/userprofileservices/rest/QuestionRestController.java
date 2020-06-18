package com.example.profileservices.userprofileservices.rest;

import java.util.List;

import com.example.profileservices.userprofileservices.communication.UserServiceCaller;
import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestion;
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

	@Autowired
	private UserServiceCaller theUserServiceCaller;

	//expose "/questions" and return list of questions
	@GetMapping("/questions")
	public List<UserConvertedQuestion> findAll(@RequestHeader (name="Authorization") String jwt){
		List<Question> questions= theQuestionService.findAll();
		return theUserServiceCaller.addUserToQuestion(questions,jwt);
	}

	//expose "/questions/{questionId}" to get single question
	@GetMapping("/questions/{questionId}")
	public UserConvertedQuestion findById(@PathVariable Long questionId, @RequestHeader (name="Authorization") String jwt){
		Question theQuestion=null;
		try{
			theQuestion= theQuestionService.findById(questionId);
		}
		catch(Exception e){
			throw new ApiRequestException("Id Not Found");
		}

		return theUserServiceCaller.addUserToSingleQuestion(theQuestion,jwt);
	}

	//expose "/user/{userId}/questions" and return list of questions asked by userId
	@GetMapping("/users/{userId}/questions")
	public List<UserConvertedQuestion> findByUserId(@PathVariable Long userId,@RequestHeader (name="Authorization") String jwt){
		try{
			List<Question> questions= theQuestionService.findByUserId(userId);
			return theUserServiceCaller.addUserToQuestion(questions,jwt);
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
