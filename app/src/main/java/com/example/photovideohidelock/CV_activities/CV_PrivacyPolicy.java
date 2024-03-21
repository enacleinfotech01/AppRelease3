package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.photovideohidelock.R;

public class CV_PrivacyPolicy extends AppCompatActivity {
    Context cv_mContext;
    WebView cv_webview;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_privacy_policy);
        this.cv_mContext = this;
        init();
    }

    private void init() {
        WebView webView = (WebView) findViewById(R.id.webview);
        this.cv_webview = webView;
        webView.loadUrl("file:///android_asset/policy/Privacy.html");
    }
}
