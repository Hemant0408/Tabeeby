package com.tabeeby.doctor.model;

/**
 * Created by Lenovo R61 on 9/20/2016.
 */
public class DataModel {
    private QuestionsModel[] questions;

    public QuestionsModel[] getQuestions ()
    {
        return questions;
    }

    public void setQuestions (QuestionsModel[] questions)
    {
        this.questions = questions;
    }

}
