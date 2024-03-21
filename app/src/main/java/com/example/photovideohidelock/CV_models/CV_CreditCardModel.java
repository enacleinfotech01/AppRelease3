package com.example.photovideohidelock.CV_models;

public class CV_CreditCardModel {
    private String card_name;
    private String card_number;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String cvc;
    private String exp_date;
    private String id;
    private String issue_date;
    private String password;
    private String ph_number;
    private String pin;
    private String start_date;
    private String url;
    private String username;

    public CV_CreditCardModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
        this.id = str;
        this.card_name = str2;
        this.card_number = str3;
        this.exp_date = str4;
        this.cvc = str5;
        this.issue_date = str6;
        this.pin = str7;
        this.start_date = str8;
        this.ph_number = str9;
        this.url = str10;
        this.username = str11;
        this.password = str12;
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

    public String getCard_name() {
        return this.card_name;
    }

    public void setCard_name(String str) {
        this.card_name = str;
    }

    public String getCard_number() {
        return this.card_number;
    }

    public void setCard_number(String str) {
        this.card_number = str;
    }

    public String getExp_date() {
        return this.exp_date;
    }

    public void setExp_date(String str) {
        this.exp_date = str;
    }

    public String getCvc() {
        return this.cvc;
    }

    public void setCvc(String str) {
        this.cvc = str;
    }

    public String getIssue_date() {
        return this.issue_date;
    }

    public void setIssue_date(String str) {
        this.issue_date = str;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String str) {
        this.pin = str;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String str) {
        this.start_date = str;
    }

    public String getPh_number() {
        return this.ph_number;
    }

    public void setPh_number(String str) {
        this.ph_number = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
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
