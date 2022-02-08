package com.example.survey.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AnswerId implements Serializable {
    private Long surveyId;
    private Long questionId;
    private Long userId;

    public AnswerId(){}

    public AnswerId(Long surveyId, Long questionId, Long userId) {
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.userId = userId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerId answerId = (AnswerId) o;

        if (!surveyId.equals(answerId.surveyId)) return false;
        if (!questionId.equals(answerId.questionId)) return false;
        return userId.equals(answerId.userId);
    }

    @Override
    public int hashCode() {
        int result = surveyId.hashCode();
        result = 31 * result + questionId.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }
}
