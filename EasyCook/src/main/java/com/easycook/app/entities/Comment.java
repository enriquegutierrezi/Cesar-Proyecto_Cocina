package com.easycook.app.entities;

public class Comment {
    private int id;
    private String content;
    private int id_user;
    private String date;

    
    public Comment(int id, String content, int id_user, String date) {
        this.id = id;
        this.content = content;
        this.id_user = id_user;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}


