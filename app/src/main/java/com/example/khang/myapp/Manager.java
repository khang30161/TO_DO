package com.example.khang.myapp;

import java.io.Serializable;

public class Manager implements Serializable {
    private String text;
    private String content;
    private String time;

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

    public Manager( String text, String content, String time) {
        this.text = text;
        this.content=content;
        this.time=time;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
