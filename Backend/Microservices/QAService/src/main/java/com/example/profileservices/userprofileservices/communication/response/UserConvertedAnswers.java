package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.models.Answer;
import com.example.profileservices.userprofileservices.util.mapper.User;

public class UserConvertedAnswers {
    private Answer answer;

    private User user;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
