package com.example.photovideohidelock.CV_models;

public class CV_AtmModel {
    private String acc_number;
    private String bank_name;
    private String card_name;
    private String card_number;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String custom5;
    private String holder_name;
    private String id;
    private String ph_number;
    private String pin;
    private String url;

    public CV_AtmModel(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.id = str;
        this.card_name = str2;
        this.bank_name = str3;
        this.pin = str4;
        this.holder_name = str5;
        this.card_number = str6;
        this.acc_number = str7;
        this.ph_number = str8;
        this.url = str9;
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

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_name(String str) {
        this.bank_name = str;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String str) {
        this.pin = str;
    }

    public String getHolder_name() {
        return this.holder_name;
    }

    public void setHolder_name(String str) {
        this.holder_name = str;
    }

    public String getCard_number() {
        return this.card_number;
    }

    public void setCard_number(String str) {
        this.card_number = str;
    }

    public String getAcc_number() {
        return this.acc_number;
    }

    public void setAcc_number(String str) {
        this.acc_number = str;
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
