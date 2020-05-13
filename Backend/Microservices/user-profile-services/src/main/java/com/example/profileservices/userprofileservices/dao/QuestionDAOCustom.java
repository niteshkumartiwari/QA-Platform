package com.example.profileservices.userprofileservices.dao;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.data.repository.query.Param;

public interface QuestionDAOCustom {
	void createQuestion(@Param("title") String title,@Param("description") String description,
			@Param("asked_by") Long askedBy, @Param("edited_by") Long editedBy,
			@Param("is_image") Short isImage, @Param("res_link") String resourceLink,
			@Param("upkudo") Long upKudo, @Param("downkudo") Long downKudo,
			@Param("is_deleted") Short isDeleted, @Param("is_banned") Short isBanned,
			@Param("last_updated") Date  lastModified ,
			@Param("thumbnail") String thumbnail);
}
