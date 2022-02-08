package com.example.survey.rest.dto;

import java.util.List;

public class SurveyInputDTO {

    private Long userId;
    List<AnswerDTO>  answerList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDTO> answerList) {
        this.answerList = answerList;
    }
}
