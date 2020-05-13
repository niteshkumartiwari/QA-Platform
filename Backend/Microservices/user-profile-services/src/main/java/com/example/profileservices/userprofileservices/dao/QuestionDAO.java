package com.example.profileservices.userprofileservices.dao;


import com.example.profileservices.userprofileservices.models.Question;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface QuestionDAO extends JpaRepository<Question, Long>, QuestionDAOCustom{
							
}
