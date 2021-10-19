package com.easycook.app.entities;

import java.util.ArrayList;

public class Blog {
    private int id;
    private String title;
    private String urlImage;
    private String content;
    private String id_Creator;
    private ArrayList <Comment> comments;    


    public Blog(int id, String title, String urlImage, String content, String id_Creator, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.content = content;
        this.id_Creator = id_Creator;
        this.comments = comments;
    }
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

