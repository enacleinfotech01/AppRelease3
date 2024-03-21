package com.example.photovideohidelock.CV_models;

import android.graphics.drawable.Drawable;

public class CV_AppList_model {
    Drawable icon;
    private String name;
    private String pkgName;

    public CV_AppList_model(String str, Drawable drawable, String str2) {
        this.name = str;
        this.icon = drawable;
        this.pkgName = str2;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }
}
