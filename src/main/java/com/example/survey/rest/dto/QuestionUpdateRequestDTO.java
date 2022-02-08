package com.example.survey.rest.dto;

import com.example.survey.model.Question;

import java.util.List;


public class QuestionUpdateRequestDTO {

    private List<Question> addList;

    private List<Question> removeList;

    private List<Question> updateList;

    public List<Question> getAddList() {
        return addList;
    }

    public void setAddList(List<Question> addList) {
        this.addList = addList;
    }

    public List<Question> getRemoveList() {
        return removeList;
    }

    public void setRemoveList(List<Question> removeList) {
        this.removeList = removeList;
    }

    public List<Question> getUpdateList() {
        return updateList;
    }

    public void setUpdateList(List<Question> updateList) {
        this.updateList = updateList;
    }
}
