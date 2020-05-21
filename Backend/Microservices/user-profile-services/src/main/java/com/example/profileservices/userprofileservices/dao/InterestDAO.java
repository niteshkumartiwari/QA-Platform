package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDAO extends JpaRepository<Interest,Long> {
}
