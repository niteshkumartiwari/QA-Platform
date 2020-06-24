package com.example.profileservices.userprofileservices.communication;

import com.example.profileservices.userprofileservices.util.mapper.Interest;
import com.example.profileservices.userprofileservices.util.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private WebClient.Builder theWebClientBuilder;

    @Value("${userservice.getAllUsers}")
    private String GET_ALL_USER_LIST;

    @Value("${userservice.getSingleUser}")
    private String GET_SINGLE_USER;

    @Value("${userservice.getAllInterests}")
    private String GET_ALL_INTEREST_LIST;

    @Override
    public List<User> getUsersList(String userList, String jwt) {
        List<User> users=theWebClientBuilder
                .defaultHeader(HttpHeaders.AUTHORIZATION,jwt)
                .build()
                .get()
                .uri(GET_ALL_USER_LIST+userList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .block();
        return users    ;
    }

    @Override
    public User getUser(String userId, String jwt) {
        User user=theWebClientBuilder
                .defaultHeader(HttpHeaders.AUTHORIZATION,jwt)
                .build()
                .get()
                .uri(GET_SINGLE_USER+userId)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        return user;
    }

    @Override
    public Map<Long,Interest> getInterestList(String interestList, String jwt) {
        Map<Long,Interest> interests=theWebClientBuilder
                .defaultHeader(HttpHeaders.AUTHORIZATION,jwt)
                .build()
                .get()
                .uri(GET_ALL_INTEREST_LIST+interestList)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<Long,Interest>>() {})
                .block();
        return interests;
    }
}
