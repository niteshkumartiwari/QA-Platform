package com.example.profileservices.userprofileservices.communication;

import com.example.profileservices.userprofileservices.util.mapper.Interest;
import com.example.profileservices.userprofileservices.util.mapper.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getUsersList(String userList,String jwt);
    User getUser(String user,String jwt);
    Map<Long,Interest> getInterestList(String interestList, String jwt);
}
