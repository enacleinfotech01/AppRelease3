package com.example.photovideohidelock.CV_models;

public class CV_SocialModel {
    private String custom1;
    private String custom2;
    private String custom3;
    private String id;
    private String name;
    private String note;
    private String password;
    private String username;
    private String website;

    public CV_SocialModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.id = str;
        this.name = str2;
        this.username = str3;
        this.password = str4;
        this.website = str5;
        this.note = str6;
        this.custom1 = str7;
        this.custom2 = str8;
        this.custom3 = str9;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String str) {
        this.website = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public String getCustom1() {
        return this.custom1;
    }

    public void setCustom1(String str) {
        this.custom1 = str;
    }

    public String getCustom2() {
        return this.custom2;
    }

    public void setCustom2(String str) {
        this.custom2 = str;
    }

    public String getCustom3() {
        return this.custom3;
    }

    public void setCustom3(String str) {
        this.custom3 = str;
    }
}
