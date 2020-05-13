package com.example.profileservices.userprofileservices.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profileservices.userprofileservices.services.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionRestController {
	@Autowired
	private QuestionService theQuestionService;
	
	@PostMapping("/quest")
	public void createQuestion(@RequestParam (value="title", required = true) String title, @RequestParam (value="description",required = true) String description,
			@RequestParam (value="asked_by",required = true) Long askedBy, @RequestParam(value="edited_by",required= false) Long editedBy,
			@RequestParam (value="is_image",required = false) Short isImage, @RequestParam(value="res_link",required= false) String resourceLink,
			@RequestParam (value="upkudo",required = false) Long upKudo, @RequestParam(value="downkudo",required= false) Long downKudo,
			@RequestParam (value="is_deleted",required = false) Short isDeleted, @RequestParam(value="is_banned",required= false) Short isBanned,
			@RequestParam (value="last_modified",required = false) Date lastModified,@RequestParam (value="thumbnail",required = false) String thumbnail) {
		theQuestionService.createQuestion(title, description, askedBy, editedBy, isImage, resourceLink, upKudo, downKudo, isDeleted, isBanned, lastModified,thumbnail);
	}
	
}
