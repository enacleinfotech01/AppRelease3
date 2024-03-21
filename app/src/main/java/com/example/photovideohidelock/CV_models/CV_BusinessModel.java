package com.example.photovideohidelock.CV_models;

public class CV_BusinessModel {
    private String company;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String date;
    private String id;
    private String pro_rating;
    private String rating;
    private String response;
    private String rewards;
    private String status;

    public CV_BusinessModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        this.id = str;
        this.company = str2;
        this.status = str3;
        this.date = str4;
        this.response = str5;
        this.pro_rating = str6;
        this.rating = str7;
        this.rewards = str8;
        this.custom1 = str9;
        this.custom2 = str10;
        this.custom3 = str11;
        this.custom4 = str12;
        this.custom5 = str13;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String str) {
        this.response = str;
    }

    public String getPro_rating() {
        return this.pro_rating;
    }

    public void setPro_rating(String str) {
        this.pro_rating = str;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getRewards() {
        return this.rewards;
    }

    public void setRewards(String str) {
        this.rewards = str;
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

    public String getCustom4() {
        return this.custom4;
    }

    public void setCustom4(String str) {
        this.custom4 = str;
    }

    public String getCustom5() {
        return this.custom5;
    }

    public void setCustom5(String str) {
        this.custom5 = str;
    }
}
