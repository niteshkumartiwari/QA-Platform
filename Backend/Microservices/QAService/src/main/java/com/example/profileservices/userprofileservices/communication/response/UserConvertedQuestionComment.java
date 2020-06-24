package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.models.QuestionComment;
import com.example.profileservices.userprofileservices.util.mapper.User;

public class UserConvertedQuestionComment {
    private QuestionComment questionComment;

    private User user;

    public QuestionComment getQuestionComment() {
        return questionComment;
    }

    public void setQuestionComment(QuestionComment questionComment) {
        this.questionComment = questionComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
