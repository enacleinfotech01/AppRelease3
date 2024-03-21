package com.example.photovideohidelock;

import android.app.Activity;
import android.content.Intent;

public class CV_Adshow {
    public static void showinterstitialAd(Activity activity, Intent intent) {
        if (intent != null) {
            activity.startActivity(intent);
        } else {
            activity.finish();
        }
    }
}
