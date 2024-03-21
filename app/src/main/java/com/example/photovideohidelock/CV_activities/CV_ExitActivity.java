package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

public class CV_ExitActivity extends AppCompatActivity {
    ImageView cv_ic_exit;
    Context cv_mContext;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_exit);
        this.cv_mContext = this;
//        AdAdmob adAdmob = new AdAdmob(this);
//        adAdmob.FullscreenAd(this);
//        adAdmob.BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        ImageView imageView = (ImageView) findViewById(R.id.ic_exit);
        this.cv_ic_exit = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ExitActivity.this.finishAffinity();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_exit, 630, 110);
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        CV_HelperResizer.putvalueinsharedprefrence(this, CV_HelperResizer.CHECK_EXIT, true);
    }
}
