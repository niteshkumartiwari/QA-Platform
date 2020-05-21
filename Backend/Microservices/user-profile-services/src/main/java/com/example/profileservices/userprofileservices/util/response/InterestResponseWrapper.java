package com.example.profileservices.userprofileservices.util.response;

import com.example.profileservices.userprofileservices.models.Interest;

import java.util.List;

public class InterestResponseWrapper {
    private List<Interest> interests;

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }
}
