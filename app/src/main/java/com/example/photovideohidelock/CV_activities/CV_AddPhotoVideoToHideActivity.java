package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_adapter.CV_HiddenImageVideoListAdapter;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.io.File;
import java.util.ArrayList;

public class CV_AddPhotoVideoToHideActivity extends AppCompatActivity {
    public static ArrayList<String> alhidden = new ArrayList<>();
    public static Boolean checkpauseoprationADD = false;
    CV_HiddenImageVideoListAdapter hiddenVideoListAdapter;
    ImageView ic_add;
    ImageView ic_back;
    Context mContext;
    RecyclerView rcv_videos;
    TextView tv_title;
    TextView txt_novideo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_add_video_to_hide);
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.mContext = this;
        init();
        resize();
    }

    private void init() {
        this.ic_back = (ImageView) findViewById(R.id.ic_back);
        this.rcv_videos = (RecyclerView) findViewById(R.id.rcv_videos);
        this.txt_novideo = (TextView) findViewById(R.id.txt_novideo);
        this.ic_add = (ImageView) findViewById(R.id.ic_add);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        if (CV_VaultOptionMainActivity.cv_image_video == 0) {
            this.txt_novideo.setText("There is no video in Vault.");
            this.tv_title.setText("Videos");
        } else {
            this.txt_novideo.setText("There is no image in Vault.");
            this.tv_title.setText("Images");
        }
        this.rcv_videos.setLayoutManager(new GridLayoutManager(this.mContext, 3));
        this.ic_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddPhotoVideoToHideActivity.checkpauseoprationADD = true;
                CV_Adshow.showinterstitialAd(CV_AddPhotoVideoToHideActivity.this, new Intent(CV_AddPhotoVideoToHideActivity.this, CV_ImageVideoFolderActivity.class));
            }
        });
        this.ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_AddPhotoVideoToHideActivity.checkpauseoprationADD = true;
                CV_AddPhotoVideoToHideActivity.this.onBackPressed();
            }
        });
    }

    private void resize() {
        CV_HelperResizer.getheightandwidth(this);
        CV_HelperResizer.setSize(this.ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.ic_add, 150, 150, true);
    }

    private class LoadVideos extends AsyncTask<Void, Void, Void> {
        private LoadVideos() {
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_AddPhotoVideoToHideActivity.alhidden.clear();
        }

        public Void doInBackground(Void... voidArr) {
            int i = 0;
            if (CV_VaultOptionMainActivity.cv_image_video == 0) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", "image");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File[] listFiles = file.listFiles();
                int length = listFiles.length;
                while (i < length) {
                    File file2 = listFiles[i];
                    if (new CV_HelperResizer.VideoFILTER().accept(file2)) {
                        CV_AddPhotoVideoToHideActivity.alhidden.add(file2.getAbsolutePath());
                    }
                    i++;
                }
                return null;
            }
            File file3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/.Calculator Vault", "image");
            if (!file3.exists()) {
                file3.mkdirs();
            }
            File[] listFiles2 = file3.listFiles();
            int length2 = listFiles2.length;
            while (i < length2) {
                File file4 = listFiles2[i];
                if (new CV_HelperResizer.ImageFileFilter().accept(file4)) {
                    CV_AddPhotoVideoToHideActivity.alhidden.add(file4.getAbsolutePath());
                }
                i++;
            }
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CV_AddPhotoVideoToHideActivity cV_AddPhotoVideoToHideActivity = CV_AddPhotoVideoToHideActivity.this;
            cV_AddPhotoVideoToHideActivity.hiddenVideoListAdapter = new CV_HiddenImageVideoListAdapter(cV_AddPhotoVideoToHideActivity, CV_AddPhotoVideoToHideActivity.alhidden);
            CV_AddPhotoVideoToHideActivity.this.rcv_videos.setAdapter(CV_AddPhotoVideoToHideActivity.this.hiddenVideoListAdapter);
            if (CV_AddPhotoVideoToHideActivity.alhidden.size() == 0) {
                CV_AddPhotoVideoToHideActivity.this.txt_novideo.setVisibility(View.VISIBLE);
                CV_AddPhotoVideoToHideActivity.this.rcv_videos.setVisibility(View.GONE);
                return;
            }
            CV_AddPhotoVideoToHideActivity.this.txt_novideo.setVisibility(View.GONE);
            CV_AddPhotoVideoToHideActivity.this.rcv_videos.setVisibility(View.VISIBLE);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        checkpauseoprationADD = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        checkpauseoprationADD = false;
        new LoadVideos().execute(new Void[0]);
    }

    public void onPause() {
        if (!checkpauseoprationADD.booleanValue()) {
            startActivity(new Intent(this, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
