package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDAO extends JpaRepository<Interest,Long> {

}
