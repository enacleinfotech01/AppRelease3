package com.example.photovideohidelock.CV_models;

public class CV_VideoModel {
    int height;
    String imageUrl;
    String title;
    int width;

    public CV_VideoModel(String str, String str2, int i, int i2) {
        this.title = str;
        this.imageUrl = str2;
        this.width = i;
        this.height = i2;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }
}
