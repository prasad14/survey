package com.example.survey.repositories;

import com.example.survey.model.Answer;
import com.example.survey.model.AnswerId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, AnswerId> {

    @Query("SELECT a FROM Answer a WHERE a.surveyId = ?1 and a.questionId = ?2")
    Iterable<Answer> findAnswersBySurveyIdAndQuestionId(Long surveyId, Long questionId);
}
