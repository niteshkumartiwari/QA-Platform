package com.example.profileservices.userprofileservices.communication.response;

import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.util.mapper.User;

import java.awt.*;

public class UserConvertedAnswerComment {
    private AnswerComment theAnswerComment;

    private User theUser;

    public AnswerComment getTheAnswerComment() {
        return theAnswerComment;
    }

    public void setTheAnswerComment(AnswerComment theAnswerComment) {
        this.theAnswerComment = theAnswerComment;
    }

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }
}
