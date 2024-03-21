package com.example.photovideohidelock.CV_models;

public class CV_EmailModel {
    private String authmethod;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String id;
    private String note;
    private String password;
    private String port;
    private String provider;
    private String server;
    private String ssl;
    private String title;
    private String type;
    private String userID;
    private String website;

    public CV_EmailModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
        this.id = str;
        this.title = str2;
        this.type = str3;
        this.userID = str4;
        this.password = str5;
        this.website = str6;
        this.server = str7;
        this.port = str8;
        this.ssl = str9;
        this.authmethod = str10;
        this.provider = str11;
        this.note = str12;
        this.custom1 = str13;
        this.custom2 = str14;
        this.custom3 = str15;
        this.custom4 = str16;
        this.custom5 = str17;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String str) {
        this.website = str;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String str) {
        this.server = str;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String str) {
        this.port = str;
    }

    public String getSsl() {
        return this.ssl;
    }

    public void setSsl(String str) {
        this.ssl = str;
    }

    public String getAuthmethod() {
        return this.authmethod;
    }

    public void setAuthmethod(String str) {
        this.authmethod = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
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
