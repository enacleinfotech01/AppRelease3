package com.example.photovideohidelock.CV_models;

import java.util.ArrayList;

public class CV_VideoFolderModel {
    String folder;
    ArrayList<CV_VideoModel> path;

    public CV_VideoFolderModel(String str, ArrayList<CV_VideoModel> arrayList) {
        this.folder = str;
        this.path = arrayList;
    }

    public String getfolder() {
        return this.folder;
    }

    public void setfolder(String str) {
        this.folder = str;
    }

    public ArrayList<CV_VideoModel> getPath() {
        return this.path;
    }

    public void setPath(ArrayList<CV_VideoModel> arrayList) {
        this.path = arrayList;
    }
}
