package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.util.mapper.User;
import com.example.profileservices.userprofileservices.util.response.UserResponse;

public class UserConvertUserResponse {
    private UserResponse userResponse;

    private User user;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
