package com.example.photovideohidelock.CV_utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import com.example.photovideohidelock.CV_models.CV_VideoModel;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class CV_HelperResizer {
    public static final String CHECK_EXIT = "GridKey";
    public static final String MyPREFERENCES = "GpsMapCamara";
    public static int SCALE_HEIGHT = 1920;
    public static int SCALE_WIDTH = 1080;
    public static int height;
    public static ArrayList<CV_VideoModel> myList = new ArrayList<>();
    public static int width;

    public static void putvalueinsharedprefrence(Context context, String str, Boolean bool) {
        SharedPreferences.Editor edit = context.getSharedPreferences(MyPREFERENCES, 0).edit();
        edit.putBoolean(str, bool.booleanValue());
        edit.apply();
    }

    public static Boolean getPREFERENCES(Context context, String str, Boolean bool) {
        return Boolean.valueOf(context.getSharedPreferences(MyPREFERENCES, 0).getBoolean(str, bool.booleanValue()));
    }

    public static void getheightandwidth(Context context) {
        getHeight(context);
        getwidth(context);
    }

    public static int getwidth(Context context) {
        int i = context.getResources().getDisplayMetrics().widthPixels;
        width = i;
        return i;
    }

    public static int getHeight(Context context) {
        int i = context.getResources().getDisplayMetrics().heightPixels;
        height = i;
        return i;
    }

    public static void setHeight(Context context, View view, int i) {
        view.getLayoutParams().height = (context.getResources().getDisplayMetrics().heightPixels * i) / SCALE_HEIGHT;
    }

    public static void setWidth(Context context, View view, int i) {
        view.getLayoutParams().width = (context.getResources().getDisplayMetrics().widthPixels * i) / SCALE_WIDTH;
    }

    public static int setHeight(int i) {
        return (height * i) / 1920;
    }

    public static int setWidth(int i) {
        return (width * i) / 1080;
    }

    public static void setSize(View view, int i, int i2) {
        view.getLayoutParams().height = setHeight(i2);
        view.getLayoutParams().width = setWidth(i);
    }

    public static void setHeightByWidth(Context context, View view, int i) {
        view.getLayoutParams().height = (context.getResources().getDisplayMetrics().widthPixels * i) / SCALE_WIDTH;
    }

    public static void setSize(View view, int i, int i2, boolean z) {
        if (z) {
            view.getLayoutParams().height = setWidth(i2);
            view.getLayoutParams().width = setWidth(i);
            return;
        }
        view.getLayoutParams().height = setHeight(i2);
        view.getLayoutParams().width = setHeight(i);
    }

    public static void setMargin(View view, int i, int i2, int i3, int i4) {
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(setWidth(i), setHeight(i2), setWidth(i3), setHeight(i4));
    }

    public static void setPadding(View view, int i, int i2, int i3, int i4) {
        view.setPadding(i, i2, i3, i4);
    }

    public static class VideoFILTER implements FileFilter {
        private final String[] okFileExtensions = {"3gp", ".mkv", ".mp4", ".ts", ".webm"};

        public boolean accept(File file) {
            for (String endsWith : this.okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ImageFileFilter implements FileFilter {
        private final String[] okFileExtensions = {"jpg", "png", "gif", "jpeg", "WEBP", "TIFF", "PSD", "RAW", "BMP", "HEIF", "INDD"};

        public boolean accept(File file) {
            for (String endsWith : this.okFileExtensions) {
                if (file.getName().toLowerCase().endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
