package com.example.survey.model;

import javax.persistence.*;

@Entity
@Table(name = "OPTIONS")
public class Options {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
