package com.example.photovideohidelock.CV_models;

public class CV_IDCardModel {
    private String address;
    private String card_name;
    private String country;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String dob;
    private String email;
    private String expdate;
    private String eyes;
    private String firstname;
    private String hair;
    private String height;
    private String id;
    private String idnumber;
    private String issuedate;
    private String lastname;
    private String sex;
    private String weight;

    public CV_IDCardModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
        this.id = str;
        this.card_name = str2;
        this.country = str3;
        this.email = str4;
        this.idnumber = str5;
        this.firstname = str6;
        this.lastname = str7;
        this.dob = str8;
        this.issuedate = str9;
        this.expdate = str10;
        this.address = str11;
        this.sex = str12;
        this.eyes = str13;
        this.hair = str14;
        this.height = str15;
        this.weight = str16;
        this.custom1 = str17;
        this.custom2 = str18;
        this.custom3 = str19;
        this.custom4 = str20;
        this.custom5 = str21;
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

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getIdnumber() {
        return this.idnumber;
    }

    public void setIdnumber(String str) {
        this.idnumber = str;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String str) {
        this.firstname = str;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String str) {
        this.lastname = str;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public String getIssuedate() {
        return this.issuedate;
    }

    public void setIssuedate(String str) {
        this.issuedate = str;
    }

    public String getExpdate() {
        return this.expdate;
    }

    public void setExpdate(String str) {
        this.expdate = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getEyes() {
        return this.eyes;
    }

    public void setEyes(String str) {
        this.eyes = str;
    }

    public String getHair() {
        return this.hair;
    }

    public void setHair(String str) {
        this.hair = str;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
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
