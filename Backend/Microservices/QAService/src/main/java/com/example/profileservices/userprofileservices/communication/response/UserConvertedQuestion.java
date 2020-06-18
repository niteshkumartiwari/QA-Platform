package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.util.mapper.User;

public class UserConvertedQuestion {
    private Question question;

    private User user;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
