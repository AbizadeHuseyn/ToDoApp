package com.example.todoapp.model.entity;

public class ToDo {

    private String uuid;
    private String title;
    private String content;

    public ToDo(String uuid, String title, String content) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
