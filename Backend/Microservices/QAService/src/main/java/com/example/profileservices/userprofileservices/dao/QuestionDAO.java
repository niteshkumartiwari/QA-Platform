package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionDAO extends JpaRepository<Question, Long>{
    Page<Question> findByAskedBy(Long userId, Pageable pageable);
    Page<Question> findAll(Pageable pageable);
}
