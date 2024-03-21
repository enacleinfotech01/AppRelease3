package com.example.photovideohidelock.CV_models;

public class CV_BankModel {
    private String acc_number;
    private String acc_type;
    private String bank_name;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String emailID;
    private String holder_name;
    private String id;
    private String mother_name;
    private String pin;
    private String pin2;
    private String route_code;
    private String sort_code;
    private String surname;
    private String swift_code;
    private String url;

    public CV_BankModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19) {
        this.id = str;
        this.bank_name = str2;
        this.acc_number = str3;
        this.holder_name = str4;
        this.acc_type = str5;
        this.pin = str6;
        this.emailID = str7;
        this.mother_name = str8;
        this.surname = str9;
        this.sort_code = str10;
        this.swift_code = str11;
        this.route_code = str12;
        this.pin2 = str13;
        this.url = str14;
        this.custom1 = str15;
        this.custom2 = str16;
        this.custom3 = str17;
        this.custom4 = str18;
        this.custom5 = str19;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_name(String str) {
        this.bank_name = str;
    }

    public String getAcc_number() {
        return this.acc_number;
    }

    public void setAcc_number(String str) {
        this.acc_number = str;
    }

    public String getHolder_name() {
        return this.holder_name;
    }

    public void setHolder_name(String str) {
        this.holder_name = str;
    }

    public String getAcc_type() {
        return this.acc_type;
    }

    public void setAcc_type(String str) {
        this.acc_type = str;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String str) {
        this.pin = str;
    }

    public String getEmailID() {
        return this.emailID;
    }

    public void setEmailID(String str) {
        this.emailID = str;
    }

    public String getMother_name() {
        return this.mother_name;
    }

    public void setMother_name(String str) {
        this.mother_name = str;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String str) {
        this.surname = str;
    }

    public String getSort_code() {
        return this.sort_code;
    }

    public void setSort_code(String str) {
        this.sort_code = str;
    }

    public String getSwift_code() {
        return this.swift_code;
    }

    public void setSwift_code(String str) {
        this.swift_code = str;
    }

    public String getRoute_code() {
        return this.route_code;
    }

    public void setRoute_code(String str) {
        this.route_code = str;
    }

    public String getPin2() {
        return this.pin2;
    }

    public void setPin2(String str) {
        this.pin2 = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
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
