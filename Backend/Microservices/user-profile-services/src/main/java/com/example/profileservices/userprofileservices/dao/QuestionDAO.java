package com.example.profileservices.userprofileservices.dao;


import com.example.profileservices.userprofileservices.models.Question;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionDAO extends JpaRepository<Question, Long>{
    List<Question> findByAskedById(Long userId);
}