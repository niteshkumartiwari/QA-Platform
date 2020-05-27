package com.platform.springsecurityjwt.util.response;


import com.platform.springsecurityjwt.models.Interest;

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
