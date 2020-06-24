package com.example.profileservices.userprofileservices.communication;

import com.example.profileservices.userprofileservices.communication.response.*;
import com.example.profileservices.userprofileservices.models.Answer;
import com.example.profileservices.userprofileservices.models.AnswerComment;
import com.example.profileservices.userprofileservices.models.Question;
import com.example.profileservices.userprofileservices.models.QuestionComment;
import com.example.profileservices.userprofileservices.util.mapper.Interest;
import com.example.profileservices.userprofileservices.util.mapper.User;
import com.example.profileservices.userprofileservices.util.response.*;
import org.LatencyUtils.IntervalEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceCallerImpl implements UserServiceCaller{
    @Autowired
    private UserService theUserService;


    @Override
    public List<UserConvertedAnswerComment> addUserToAnswerComment(List<AnswerComment> list,String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getRepliedBy().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertedAnswerComment> result= new ArrayList<>();

        //TO-DO: For invalid UserIds size of answercommentlist and userlist will vary
        for(int i=0;i<usersList.size();i++){
            UserConvertedAnswerComment tmp= new UserConvertedAnswerComment();
            tmp.setTheAnswerComment(list.get(i));
            tmp.setTheUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public List<UserConvertedAnswers> addUserToAnswer(List<Answer> list, String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getAnsweredBy().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertedAnswers> result= new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            UserConvertedAnswers tmp= new UserConvertedAnswers();
            tmp.setAnswer(list.get(i));
            tmp.setUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public List<UserConvertedUserDateResponse> addUserToUserDateResponse(List<UserDateResponse> list, String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getUserId().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertedUserDateResponse> result= new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            UserConvertedUserDateResponse tmp= new UserConvertedUserDateResponse();
            tmp.setUserDateResponse(list.get(i));
            tmp.setUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public List<UserConvertedQuestionComment> addUserToQuestionComment(List<QuestionComment> list,String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getRepliedBy().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertedQuestionComment> result= new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            UserConvertedQuestionComment tmp= new UserConvertedQuestionComment();
            tmp.setQuestionComment(list.get(i));
            tmp.setUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public List<UserConvertUserResponse> addUserToUserResponse(List<UserResponse> list,String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getUserId().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertUserResponse> result= new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            UserConvertUserResponse tmp= new UserConvertUserResponse();
            tmp.setUserResponse(list.get(i));
            tmp.setUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public List<UserConvertedQuestion> addUserToQuestion(List<Question> list, String jwt) {
        String userIds="";
        for(int i=0;i<list.size();i++){
            userIds+=list.get(i).getAskedBy().toString();
            if(i!= list.size()-1) userIds+=",";
        }

        List<User> usersList= theUserService.getUsersList(userIds,jwt);
        List<UserConvertedQuestion> result= new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            UserConvertedQuestion tmp= new UserConvertedQuestion();
            tmp.setQuestion(list.get(i));
            tmp.setUser(usersList.get(i));

            result.add(tmp);
        }

        return result;
    }

    @Override
    public UserConvertedQuestion addUserToSingleQuestion(Question question, String jwt) {
        User user= theUserService.getUser(question.getAskedBy().toString(),jwt);

        UserConvertedQuestion result= new UserConvertedQuestion();
        result.setQuestion(question);
        result.setUser(user);

        return result;
    }

    @Override
    public List<Interest> addInterestToInterestResponse(List<InterestResponse> list,String jwt) {
        String interestIds="";
        for(int i=0;i<list.size();i++){
            interestIds+=list.get(i).getInterestId().toString();
            if(i!= list.size()-1) interestIds+=",";
        }

        Map<Long, Interest> result= theUserService.getInterestList(interestIds,jwt);
        List<Interest> interests= new ArrayList<>();

        for(InterestResponse theInterestResponse: list){
            if(result.containsKey(theInterestResponse.getInterestId()));
                interests.add(result.get(theInterestResponse.getInterestId()));
        }

        return interests;
    }
}
