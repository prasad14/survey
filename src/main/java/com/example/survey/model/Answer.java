package com.example.survey.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Answer")
public class Answer {

    @EmbeddedId
    private AnswerId answerId;

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerId getAnswerId() {
        return answerId;
    }

    public void setAnswerId(AnswerId answerId) {
        this.answerId = answerId;
    }
}
