package com.example.photovideohidelock.CV_models;

public class CV_ECommerceModel {
    private String address;
    private String card_name;
    private String city;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String emailID;
    private String first_name;
    private String id;
    private String password;
    private String ph_number;
    private String username;

    public CV_ECommerceModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.id = str;
        this.card_name = str2;
        this.first_name = str3;
        this.username = str4;
        this.password = str5;
        this.emailID = str6;
        this.ph_number = str7;
        this.address = str8;
        this.city = str9;
        this.custom1 = str10;
        this.custom2 = str11;
        this.custom3 = str12;
        this.custom4 = str13;
        this.custom5 = str14;
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

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String str) {
        this.first_name = str;
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

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(String str) {
        this.emailID = str;
    }

    public String getPh_number() {
        return this.ph_number;
    }

    public void setPh_number(String str) {
        this.ph_number = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
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
