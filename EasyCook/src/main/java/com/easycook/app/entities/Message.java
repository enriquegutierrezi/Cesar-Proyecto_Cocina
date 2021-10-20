package com.easycook.app.entities;

public class Message {
    public static final String COLLECTION_NAME = "messages";

    private int id;
    private String from;
    private String to;
    private String content;
    private Boolean status;

    public Message(int id, String from, String to, String content, Boolean status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
