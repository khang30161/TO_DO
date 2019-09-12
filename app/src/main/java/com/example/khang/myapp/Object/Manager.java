package com.example.khang.myapp.Object;

import java.io.Serializable;

public class Manager implements Serializable {
    private String text;
    private String content;
    private String time;
    private String finish;

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Manager(String text, String content, String time, String finish) {
        this.text = text;
        this.content = content;
        this.time = time;
        this.finish=finish;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}