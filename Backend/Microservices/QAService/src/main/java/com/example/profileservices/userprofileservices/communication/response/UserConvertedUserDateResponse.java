package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.util.mapper.User;
import com.example.profileservices.userprofileservices.util.response.UserDateResponse;

public class UserConvertedUserDateResponse {
    private UserDateResponse userDateResponse;

    private User user;

    public UserDateResponse getUserDateResponse() {
        return userDateResponse;
    }

    public void setUserDateResponse(UserDateResponse userDateResponse) {
        this.userDateResponse = userDateResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
