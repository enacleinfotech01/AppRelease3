package com.example.photovideohidelock.CV_models;

import java.io.Serializable;

public class CV_NotesModel implements Serializable {
    String date;
    String id;
    String note;
    String time;
    String title;

    public CV_NotesModel(String str, String str2, String str3, String str4, String str5) {
        this.id = str;
        this.title = str2;
        this.note = str3;
        this.date = str4;
        this.time = str5;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }
}
