package com.example.photovideohidelock.CV_activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_adapter.CV_ImageVideoFolderAdapter;
import com.example.photovideohidelock.CV_models.CV_VideoFolderModel;
import com.example.photovideohidelock.CV_models.CV_VideoModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.io.File;
import java.util.ArrayList;

public class CV_ImageVideoFolderActivity extends AppCompatActivity {
    public static Boolean cv_checkpauseoperationimg_video = false;
    public static Activity cv_mActivity;
    private FrameLayout cv_adContainerView;
    ImageView cv_back;
    ImageView cv_done;
    CV_ImageVideoFolderAdapter cv_folderAdapter;
    ArrayList<CV_VideoFolderModel> cv_folderData = new ArrayList<>();
    RecyclerView cv_list;
    Activity cv_mContext;
    ArrayList<CV_VideoModel> cv_myVideo = new ArrayList<>();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_video_folder);
        cv_mActivity = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        this.cv_mContext = this;
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_back = (ImageView) findViewById(R.id.back);
        this.cv_done = (ImageView) findViewById(R.id.done);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        this.cv_list = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        new cv_Get_Video().execute(new String[0]);
        this.cv_done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_HelperResizer.myList.size() == 0) {
                    Toast.makeText(CV_ImageVideoFolderActivity.this.cv_mContext, "Please Select At least One Video", Toast.LENGTH_SHORT).show();
                } else {
                    CV_ImageVideoFolderActivity.this.finish();
                }
            }
        });
        this.cv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ImageVideoFolderActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_done, 90, 90, true);
    }

    public class cv_Get_Video extends AsyncTask<String, String, String> {
        cv_Get_Video() {
        }

        public void onPreExecute() {
            super.onPreExecute();
        }

        public String doInBackground(String... strArr) {
            Cursor cursor;
            if (CV_VaultOptionMainActivity.cv_image_video == 0) {
                cursor = CV_ImageVideoFolderActivity.this.cv_mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_display_name", "width", "height"}, (String) null, (String[]) null, "date_modified DESC");
            } else {
                cursor = CV_ImageVideoFolderActivity.this.cv_mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_display_name", "width", "height"}, (String) null, (String[]) null, "date_modified DESC");
            }
            if (cursor != null || !cursor.moveToFirst()) {
                CV_ImageVideoFolderActivity.this.cv_folderData.clear();
                do {
                    try {
                        @SuppressLint("Range") String string = cursor.getString(cursor.getColumnIndex("_display_name"));
                        @SuppressLint("Range") String string2 = cursor.getString(cursor.getColumnIndex("width"));
                        @SuppressLint("Range") String string3 = cursor.getString(cursor.getColumnIndex("height"));
                        @SuppressLint("Range") String string4 = cursor.getString(cursor.getColumnIndex("_data"));
                        int parseInt = Integer.parseInt(string2);
                        int parseInt2 = Integer.parseInt(string3);
                        if (!string.endsWith(".gif")) {
                            CV_ImageVideoFolderActivity.this.cv_myVideo.add(new CV_VideoModel(string, string4, parseInt, parseInt2));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
                for (int i = 0; i < CV_ImageVideoFolderActivity.this.cv_myVideo.size(); i++) {
                    String parent = new File(CV_ImageVideoFolderActivity.this.cv_myVideo.get(i).getImageUrl()).getParent();
                    boolean z = false;
                    int i2 = 0;
                    for (int i3 = 0; i3 < CV_ImageVideoFolderActivity.this.cv_folderData.size(); i3++) {
                        if (CV_ImageVideoFolderActivity.this.cv_folderData.get(i3).getfolder().equals(parent)) {
                            z = true;
                            i2 = i3;
                        }
                    }
                    if (z) {
                        CV_ImageVideoFolderActivity.this.cv_folderData.get(i2).getPath().add(CV_ImageVideoFolderActivity.this.cv_myVideo.get(i));
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(CV_ImageVideoFolderActivity.this.cv_myVideo.get(i));
                        CV_ImageVideoFolderActivity.this.cv_folderData.add(new CV_VideoFolderModel(parent, arrayList));
                    }
                }
                return null;
            }
            cursor.close();
            CV_ImageVideoFolderActivity.this.cv_folderData.clear();
            return null;
        }

        public void onPostExecute(String str) {
            super.onPostExecute(str);
            CV_ImageVideoFolderActivity cV_ImageVideoFolderActivity = CV_ImageVideoFolderActivity.this;
            cV_ImageVideoFolderActivity.cv_folderAdapter = new CV_ImageVideoFolderAdapter(cV_ImageVideoFolderActivity.cv_mContext, CV_ImageVideoFolderActivity.this.cv_folderData);
            CV_ImageVideoFolderActivity.this.cv_list.setAdapter(CV_ImageVideoFolderActivity.this.cv_folderAdapter);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        cv_checkpauseoperationimg_video = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        cv_checkpauseoperationimg_video = false;
    }

    public void onPause() {
        if (!cv_checkpauseoperationimg_video.booleanValue()) {
            startActivity(new Intent(this, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
