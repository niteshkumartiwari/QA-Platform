package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.dao.InterestDAO;
import com.platform.springsecurityjwt.exception.ApiRequestException;
import com.platform.springsecurityjwt.models.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class InterestServiceImpl implements InterestService{
    @Autowired
    private InterestDAO theInterestDAO;

    @Override
    public List<Interest> findAllInterests() {
        try{
            return theInterestDAO.findAll();
        }
        catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Page<Interest> findAllInterestsByPages(int currentPage, int numberOfElementsPerPage, String sortParam) {
        return theInterestDAO.findAll(PageRequest.of(currentPage,numberOfElementsPerPage, Sort.by(sortParam)));
    }

    @Override
    public Map<Long,Interest> findAllInterestsByIds(List<Long> interestIds) {
        Map<Long,Interest> interests= new HashMap<>();

        for(Long interestId: interestIds){
            Optional result=theInterestDAO.findById(interestId);
            if(result.isPresent()){
                interests.put(interestId,(Interest) result.get());
            }
        }

        return  interests;
    }

    @Override
    public Interest findById(Long interestId) {
        if(!theInterestDAO.findById(interestId).isPresent()){
            throw new ApiRequestException("InterestId Not Found.");
        }

        return theInterestDAO.findById(interestId).get();
    }

    @Override
    public void addInterest(Interest interest) {
        interest.setId(Long.valueOf(0));

        theInterestDAO.save(interest);
    }

    @Override
    public void updateInterest(Interest interest) {
        theInterestDAO.save(interest);
    }

    @Override
    public void deleteInterest(Long interestId) {
        try{
            theInterestDAO.deleteById(interestId);
        }
        catch (Exception e){
            throw new ApiRequestException("Invalid interestId.");
        }
    }
}
