package com.easycook.app.entities;

import java.util.ArrayList;

public class Blog {
    private int id;
    private String content;
    private String id_Creator;
    private ArrayList <Comment> comments;    


    public Blog(int id, String content, String id_Creator, ArrayList<Comment> comments) {
        this.id = id;
        this.content = content;
        this.id_Creator = id_Creator;
        this.comments = comments;
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
    public String getId_Creator() {
        return id_Creator;
    }
    public void setId_Creator(String id_Creator) {
        this.id_Creator = id_Creator;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

}

