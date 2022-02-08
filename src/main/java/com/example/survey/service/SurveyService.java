package com.example.survey.service;

import com.example.survey.model.Answer;
import com.example.survey.model.Question;
import com.example.survey.model.Survey;
import com.example.survey.rest.dto.AnswerDTO;
import com.example.survey.rest.dto.QuestionUpdateRequestDTO;
import com.example.survey.rest.dto.SurveyInputDTO;
import com.example.survey.rest.dto.SurveyResponseDTO;

import java.util.List;

public interface SurveyService {

    Survey createSurvey(Survey survey);

    List<Survey> fetchAllSurveys();

    void updateQuestionOfSurvey(Long surveyId, QuestionUpdateRequestDTO questionUpdateRequest);

    Survey getSurveyById(Long valueOf);

    void updateQuestion(Long surveyId, Long questionId, Question question);

    void deleteQuestion(Long surveyId, Long questionId);

    Survey addQuestion(Long surveyId, Question question);

    void removeSurvey(Long surveyId);

    void submitSurvey(Long surveyId, SurveyInputDTO surveyInputDTO);

    void deleteAnswerForAUser(Long surveyId, Long questionId, Long userId);

    Answer updateAnswerForAUser(Long surveyId, Long questionId, Long userId, AnswerDTO answerDTO);

    Answer addAnswerForAUser(Long surveyId, Long questionId, Long userId, AnswerDTO answerDTO);

    Answer getAnswersOfAUser(Long surveyId, Long questionId, Long userId);

    SurveyResponseDTO getAnswersOfAQuestion(Long surveyId, Long questionId);
}
