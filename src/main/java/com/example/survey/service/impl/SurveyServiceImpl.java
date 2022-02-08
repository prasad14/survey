package com.example.survey.service.impl;

import com.example.survey.exception.DataNotFoundException;
import com.example.survey.model.Answer;
import com.example.survey.model.AnswerId;
import com.example.survey.model.Question;
import com.example.survey.model.Survey;

import com.example.survey.repositories.AnswerRepository;
import com.example.survey.repositories.QuestionRepository;
import com.example.survey.repositories.SurveyRepository;
import com.example.survey.rest.dto.AnswerDTO;
import com.example.survey.rest.dto.QuestionUpdateRequestDTO;
import com.example.survey.rest.dto.SurveyInputDTO;
import com.example.survey.rest.dto.SurveyResponseDTO;
import com.example.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        Survey surveyResp = surveyRepository.save(survey);
        return surveyResp;
    }

    @Override
    public List<Survey> fetchAllSurveys() {
        Iterable<Survey> surveyIterable = surveyRepository.findAll();
        List<Survey> surveys = new ArrayList<>();
        surveyIterable.forEach(surveys::add);
        return surveys;
    }

    @Override
    public void updateQuestionOfSurvey(Long surveyId, QuestionUpdateRequestDTO questionUpdateRequest) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) {
            throw new DataNotFoundException("Survey not found for Id : " + surveyId);
        }
        Survey survey = surveyOptional.get();

        List<Question> questionList = survey.getQuestionList();

        if (!CollectionUtils.isEmpty(questionUpdateRequest.getAddList()))
            questionList.addAll(questionUpdateRequest.getAddList());

        if (!CollectionUtils.isEmpty(questionUpdateRequest.getRemoveList()))
            updateQuestionsList(questionList, questionUpdateRequest.getRemoveList(), "DELETE");

        if (!CollectionUtils.isEmpty(questionUpdateRequest.getUpdateList()))
            updateQuestionsList(questionList, questionUpdateRequest.getUpdateList(), "UPDATE");

        surveyRepository.save(survey);
    }

    private void updateQuestionsList(List<Question> questionList, List<Question> updateList, String action) {
        int size = questionList.size();
        for (int i = 0; i < size; i++) {
            for (Question question : updateList) {
                if (null != questionList.get(i).getId() && questionList.get(i).getId().equals(question.getId())) {
                    if (action.equals("UPDATE")) {
                        questionList.set(i, question);
                    } else if (action.equals("DELETE")) {
                        questionList.remove(i);
                        i--;
                        size--;
                    }
                }
            }
        }
    }

    @Override
    public Survey getSurveyById(Long surveyId) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) {
            throw new DataNotFoundException("Survey not found for Id : " + surveyId);
        }
        return surveyOptional.get();
    }

    @Override
    public void updateQuestion(Long surveyId, Long questionId, Question question) {

    }

    @Override
    public void deleteQuestion(Long surveyId, Long questionId) {


    }

    @Override
    public Survey addQuestion(Long surveyId, Question question) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) {
            throw new DataNotFoundException("Survey not found for Id : " + surveyId);
        }
        Survey survey = surveyOptional.get();

        List<Question> questionList = survey.getQuestionList();
        questionList.add(question);
        return surveyRepository.save(survey);
    }

    @Override
    public void removeSurvey(Long surveyId) {
        surveyRepository.deleteById(surveyId);
    }

    @Override
    public void submitSurvey(Long surveyId, SurveyInputDTO surveyInputDTO) {

        List<AnswerDTO> answerDTOS = surveyInputDTO.getAnswerList();
        Long userId = surveyInputDTO.getUserId();

        for (AnswerDTO answerDTO : answerDTOS) {
            AnswerId answerId = new AnswerId(surveyId, answerDTO.getQuestionId(), userId);
            Answer answer = new Answer();
            answer.setAnswer(answerDTO.getAnswer());
            answer.setAnswerId(answerId);
            answerRepository.save(answer);
        }
    }

    @Override
    public void deleteAnswerForAUser(Long surveyId, Long questionId, Long userId) {
        AnswerId answerId = new AnswerId(surveyId, questionId, userId);
        answerRepository.deleteById(answerId);
    }

    @Override
    public Answer updateAnswerForAUser(Long surveyId, Long questionId, Long userId, AnswerDTO answerDTO) {
        AnswerId answerId = new AnswerId(surveyId, questionId, userId);
        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        if (answerOptional.isEmpty()) {
            throw new DataNotFoundException("Answer not found for Id : " + answerId);
        }
        Answer answer = answerOptional.get();
        answer.setAnswer(answerDTO.getAnswer());
        return answerRepository.save(answer);
    }

    @Override
    public Answer addAnswerForAUser(Long surveyId, Long questionId, Long userId, AnswerDTO answerDTO) {
        AnswerId answerId = new AnswerId(surveyId, questionId, userId);
        Answer answer = new Answer();
        answer.setAnswer(answerDTO.getAnswer());
        answer.setAnswerId(answerId);
        return answerRepository.save(answer);
    }

    @Override
    public Answer getAnswersOfAUser(Long surveyId, Long questionId, Long userId) {
        AnswerId answerId = new AnswerId(surveyId, questionId, userId);
        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        if (answerOptional.isEmpty()) {
            throw new DataNotFoundException("Answer not found for Id : " + answerId);
        }
        return answerOptional.get();
    }

    @Override
    public SurveyResponseDTO getAnswersOfAQuestion(Long surveyId, Long questionId) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isEmpty()) {
            throw new DataNotFoundException("Survey not found for Id : " + surveyId);
        }
        Survey survey = surveyOptional.get();

        List<Question> questionList = survey.getQuestionList();
        Optional<Question>   questionOptional = questionList.stream().filter(q -> q.getId().equals(questionId)).findFirst();
        if (questionOptional.isEmpty()) {
            throw new DataNotFoundException("Question not found for Id : " + surveyId);
        }
        Question question = questionOptional.get();

        SurveyResponseDTO surveyResponseDTO  = new SurveyResponseDTO();
        surveyResponseDTO.setQuestion(question);
        Iterable<Answer> answerIterable = answerRepository.findAnswersBySurveyIdAndQuestionId(surveyId,questionId);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answerIterable) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setAnswer(answer.getAnswer());
            answerDTO.setUserId(answer.getAnswerId().getUserId());
            answerDTOS.add(answerDTO);
        }
        surveyResponseDTO.setAnswers(answerDTOS);
        return surveyResponseDTO;
    }
}
