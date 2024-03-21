package com.example.photovideohidelock.CV_models;

import java.io.Serializable;

public class CV_ContactModel implements Serializable {
    String id;
    String lookupkey;
    String name;
    String number;

    public CV_ContactModel() {
    }

    public CV_ContactModel(String str, String str2, String str3) {
        this.lookupkey = str;
        this.name = str2;
        this.number = str3;
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

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getLookupkey() {
        return this.lookupkey;
    }

    public void setLookupkey(String str) {
        this.lookupkey = str;
    }
}
