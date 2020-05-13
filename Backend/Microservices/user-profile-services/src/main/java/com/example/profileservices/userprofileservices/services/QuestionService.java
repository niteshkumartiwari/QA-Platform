package com.example.profileservices.userprofileservices.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;


public interface QuestionService {
	void createQuestion(@RequestBody (required = true) String title, @RequestBody (required = true) String description,
						@RequestBody (required = true) Long askedBy, @RequestBody(required= false) Long editedBy,
						@RequestBody (required = false) Short isImage, @RequestBody(required= false) String resourceLink,
						@RequestBody (required = false) Long upKudo, @RequestBody(required= false) Long downKudo,
						@RequestBody (required = false) Short isDeleted, @RequestBody(required= false) Short isBanned,
						@RequestBody (required = false) Date lastUpdated,
						@RequestBody (required=false) String thumbnail);
}
