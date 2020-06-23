package com.example.profileservices.userprofileservices.util.decorater;

import com.example.profileservices.userprofileservices.communication.response.UserConvertedQuestion;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Builder
@Getter
public class QuestionDecorator {
    private List<UserConvertedQuestion> theUserConvertedQuestions;
    private Long totalElements;
    private int totalPages;
    private int number;
    private Pageable pageable;
    private int size;
    private Sort sort;
}
