package com.example.survey.rest.dto;

import com.example.survey.model.Question;

import java.util.List;

public class SurveyResponseDTO {

    private Question question;

    private List<AnswerDTO> answers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
