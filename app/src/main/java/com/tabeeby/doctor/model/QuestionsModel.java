package com.tabeeby.doctor.model;

/**
 * Created by Lenovo R61 on 9/20/2016.
 */
public class QuestionsModel {
    private String question_title;

    private String user_type;

    private String question_text;

    private String answer_count;

    private String category;

    private String username;

    private String user_id;

    private String question_id;

    private String user_picture;

    private String view_count;

    private String fullname;

    private String picture_path;

    private String question_created;

    public String getQuestion_title ()
    {
        return question_title;
    }

    public void setQuestion_title (String question_title)
    {
        this.question_title = question_title;
    }

    public String getUser_type ()
    {
        return user_type;
    }

    public void setUser_type (String user_type)
    {
        this.user_type = user_type;
    }

    public String getQuestion_text ()
    {
        return question_text;
    }

    public void setQuestion_text (String question_text)
    {
        this.question_text = question_text;
    }

    public String getAnswer_count ()
    {
        return answer_count;
    }

    public void setAnswer_count (String answer_count)
    {
        this.answer_count = answer_count;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public String getQuestion_id ()
    {
        return question_id;
    }

    public void setQuestion_id (String question_id)
    {
        this.question_id = question_id;
    }

    public String getUser_picture ()
    {
        return user_picture;
    }

    public void setUser_picture (String user_picture)
    {
        this.user_picture = user_picture;
    }

    public String getView_count ()
    {
        return view_count;
    }

    public void setView_count (String view_count)
    {
        this.view_count = view_count;
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

    public String getQuestion_created ()
    {
        return question_created;
    }

    public void setQuestion_created (String question_created)
    {this.question_created = question_created;}

}
