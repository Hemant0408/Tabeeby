package com.tabeeby.doctor.model;

/**
 * Created by Lenovo R61 on 9/22/2016.
 */

public class AnswerModel
{
    private String username;

    private String answer_created;

    private String user_type;

    private String answer_id;

    private String user_id;

    private String user_picture;

    private String fullname;

    private String picture_path;

    private String answer_text;


    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getQuestion_category() {
        return question_category;
    }

    public void setQuestion_category(String question_category) {
        this.question_category = question_category;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_view_count() {
        return question_view_count;
    }

    public void setQuestion_view_count(String question_view_count) {
        this.question_view_count = question_view_count;
    }

    public String getQuastion_answer_count() {
        return quastion_answer_count;
    }

    public void setQuastion_answer_count(String quastion_answer_count) {
        this.quastion_answer_count = quastion_answer_count;
    }

    public String getQuastion_ask_by() {
        return quastion_ask_by;
    }

    public void setQuastion_ask_by(String quastion_ask_by) {
        this.quastion_ask_by = quastion_ask_by;
    }

    public String getQuastion_Ask_pic() {
        return quastion_Ask_pic;
    }

    public void setQuastion_Ask_pic(String quastion_Ask_pic) {
        this.quastion_Ask_pic = quastion_Ask_pic;
    }

    private String question_title;

    private String question_text;

    private String question_category;

    private String question_id;

    private String question_view_count;

    private String quastion_answer_count;

    private String quastion_ask_by;

    private String quastion_Ask_pic;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getAnswer_created ()
    {
        return answer_created;
    }

    public void setAnswer_created (String answer_created)
    {
        this.answer_created = answer_created;
    }

    public String getUser_type ()
    {
        return user_type;
    }

    public void setUser_type (String user_type)
    {
        this.user_type = user_type;
    }

    public String getAnswer_id ()
    {
        return answer_id;
    }

    public void setAnswer_id (String answer_id)
    {
        this.answer_id = answer_id;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getUser_picture ()
    {
        return user_picture;
    }

    public void setUser_picture (String user_picture)
    {
        this.user_picture = user_picture;
    }

    public String getFullname ()
    {
        return fullname;
    }

    public void setFullname (String fullname)
    {
        this.fullname = fullname;
    }

    public String getPicture_path ()
    {
        return picture_path;
    }

    public void setPicture_path (String picture_path)
    {
        this.picture_path = picture_path;
    }

    public String getAnswer_text ()
    {
        return answer_text;
    }

    public void setAnswer_text (String answer_text)
    {
        this.answer_text = answer_text;
    }

}
