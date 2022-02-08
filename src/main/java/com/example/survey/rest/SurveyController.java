package com.example.survey.rest;

import com.example.survey.model.Answer;
import com.example.survey.model.Question;
import com.example.survey.model.Survey;
import com.example.survey.rest.dto.AnswerDTO;
import com.example.survey.rest.dto.QuestionUpdateRequestDTO;
import com.example.survey.rest.dto.SurveyInputDTO;
import com.example.survey.rest.dto.SurveyResponseDTO;
import com.example.survey.service.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SurveyController {

    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

    @Autowired
    private SurveyService surveyService;

    @PostMapping("/survey")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        Survey surveyResponse = surveyService.createSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);
    }

    @GetMapping("/surveys/{surveyId}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable("surveyId") Long surveyId) {
        Survey survey = surveyService.getSurveyById(surveyId);
        return ResponseEntity.status(HttpStatus.OK).body(survey);
    }

    @GetMapping("/surveys")
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = surveyService.fetchAllSurveys();
        return ResponseEntity.status(HttpStatus.OK).body(surveys);
    }

    @DeleteMapping("/surveys/{surveyId}")
    public ResponseEntity<Object> deleteSurvey(@PathVariable("surveyId") Long surveyId) {
        surveyService.removeSurvey(surveyId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/surveys/{surveyId}/updateQuestions")
    public ResponseEntity<Object> updateQuestionOfSurvey(@PathVariable("surveyId") Long surveyId,
                                                         @RequestBody QuestionUpdateRequestDTO questionUpdateRequest) {
        surveyService.updateQuestionOfSurvey(surveyId, questionUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Object> addQuestion(@PathVariable("surveyId") Long surveyId, @RequestBody Question question) {
        surveyService.addQuestion(surveyId, question);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<Object> updateQuestion(@PathVariable("surveyId") Long surveyId,
                                                 @PathVariable("questionId") Long questionId,
                                                 @RequestBody Question question) {
        surveyService.updateQuestion(surveyId, questionId, question);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable("surveyId") Long surveyId,
                                                 @PathVariable("questionId") Long questionId) {
        surveyService.deleteQuestion(surveyId, questionId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/surveys/{surveyId}/submitSurvey")
    public ResponseEntity<Object> submitSurvey(@PathVariable("surveyId") Long surveyId,
                                               @RequestBody SurveyInputDTO surveyInputDTO) {
        surveyService.submitSurvey(surveyId, surveyInputDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/users/{userId}/surveys/{surveyId}/questions/{questionId}/answer")
    public ResponseEntity<Object> deleteAnswerForAUser(@PathVariable("surveyId") Long surveyId,
                                                       @PathVariable("questionId") Long questionId,
                                                       @PathVariable("userId") Long userId) {
        surveyService.deleteAnswerForAUser(surveyId, questionId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/users/{userId}/surveys/{surveyId}/questions/{questionId}/answer")
    public ResponseEntity<Answer> updateAnswerForAUser(@PathVariable("surveyId") Long surveyId,
                                                       @PathVariable("questionId") Long questionId,
                                                       @PathVariable("userId") Long userId,
                                                       @RequestBody AnswerDTO answerDTO) {
        Answer answer = surveyService.updateAnswerForAUser(surveyId, questionId, userId, answerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @PostMapping("/users/{userId}/surveys/{surveyId}/questions/{questionId}/answer")
    public ResponseEntity<Answer> addAnswerForAUser(@PathVariable("surveyId") Long surveyId,
                                                    @PathVariable("questionId") Long questionId,
                                                    @PathVariable("userId") Long userId,
                                                    @RequestBody AnswerDTO answerDTO) {
        Answer answer = surveyService.addAnswerForAUser(surveyId, questionId, userId, answerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping("/users/{userId}/surveys/{surveyId}/questions/{questionId}/answer")
    public ResponseEntity<Answer> getAnswersOfAUser(@PathVariable("surveyId") Long surveyId,
                                                    @PathVariable("questionId") Long questionId,
                                                    @PathVariable("userId") Long userId) {
        Answer answer = surveyService.getAnswersOfAUser(surveyId, questionId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @GetMapping("surveys/{surveyId}/questions/{questionId}/answer")
    public ResponseEntity<SurveyResponseDTO> getAnswersOfAQuestion(@PathVariable("surveyId") Long surveyId,
                                                                   @PathVariable("questionId") Long questionId) {
        SurveyResponseDTO surveyResponseDTO = surveyService.getAnswersOfAQuestion(surveyId, questionId);
        return ResponseEntity.status(HttpStatus.OK).body(surveyResponseDTO);
    }



}

