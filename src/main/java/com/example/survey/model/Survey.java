package com.example.survey.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SURVEY")
public class Survey {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String surveyName;
    private String createdUserName;
    private String expiringDate;

    @OneToMany(targetEntity = Question.class, fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }
}
