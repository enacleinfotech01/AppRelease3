package com.example.photovideohidelock.CV_models;

public class CV_GeneralModel {
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String id;
    private String title;

    public CV_GeneralModel(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = str;
        this.title = str2;
        this.custom1 = str3;
        this.custom2 = str4;
        this.custom3 = str5;
        this.custom4 = str6;
        this.custom5 = str7;
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
