package com.example.profileservices.userprofileservices.util.response;

import java.util.List;

public class UserReputationResponseWrapper {
    List<UserReputationResponse> userReputationResponses;

    public List<UserReputationResponse> getUserReputationResponses() {
        return userReputationResponses;
    }

    public void setUserReputationResponses(List<UserReputationResponse> userReputationResponses) {
        this.userReputationResponses = userReputationResponses;
    }
}
