package com.example.profileservices.userprofileservices.communication;

import com.example.profileservices.userprofileservices.communication.response.*;
import com.example.profileservices.userprofileservices.models.Answer;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionComment;
import com.example.profileservices.userprofileservices.util.mapper.Interest;
import com.example.profileservices.userprofileservices.util.response.*;

import java.util.List;

public interface UserServiceCaller {
    public List<UserConvertedAnswerComment> addUserToAnswerComment(List<AnswerComment> list, String jwt);
    public List<UserConvertedAnswers> addUserToAnswer(List<Answer> list, String jwt);
    public List<UserConvertedUserDateResponse> addUserToUserDateResponse(List<UserDateResponse> list, String jwt);
    public List<UserConvertedQuestionComment> addUserToQuestionComment(List<QuestionComment> list,String jwt);
    public List<UserConvertUserResponse> addUserToUserResponse(List<UserResponse> list,String jwt);
    public List<UserConvertedQuestion> addUserToQuestion(List<Question> list,String jwt);
    public UserConvertedQuestion addUserToSingleQuestion(Question question,String jwt);
    public List<Interest> addInterestToInterestResponse(List<InterestResponse> list, String jwt);
}
