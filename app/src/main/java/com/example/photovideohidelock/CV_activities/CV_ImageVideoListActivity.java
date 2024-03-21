package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_adapter.CV_ImageVideoListAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_models.CV_VideoFolderModel;
import com.example.photovideohidelock.CV_models.CV_VideoModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.CV_utils.CV_RelativePopupWindow;
import com.example.photovideohidelock.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CV_ImageVideoListActivity extends AppCompatActivity {
    public static Boolean cv_checkpauseoperation_img_video_list = false;
    public static CV_VideoFolderModel cv_imageFolderData;
    ImageView cv_back;
    ImageView cv_ic_lock;
    ImageView cv_ic_option;
    CV_ImageVideoListAdapter cv_imageListAdapter;
    View cv_iv_view;
    Context cv_mContext;
    /* access modifiers changed from: private */
    public ArrayList<CV_VideoModel> cv_myImages;
    TextView cv_tv_title;
    RecyclerView cv_videoList;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_list_video);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_back = (ImageView) findViewById(R.id.back);
        this.cv_videoList = (RecyclerView) findViewById(R.id.videoList);
        this.cv_ic_option = (ImageView) findViewById(R.id.ic_option);
        this.cv_ic_lock = (ImageView) findViewById(R.id.ic_lock);
        this.cv_iv_view = findViewById(R.id.iv_view);
        this.cv_tv_title = (TextView) findViewById(R.id.tv_title);
        if (CV_VaultOptionMainActivity.cv_image_video == 0) {
            this.cv_tv_title.setText("All Videos");
        } else {
            this.cv_tv_title.setText("All Images");
        }
        this.cv_videoList.setLayoutManager(new GridLayoutManager(this, 3));
        this.cv_myImages = cv_imageFolderData.getPath();
        CV_ImageVideoListAdapter cV_ImageVideoListAdapter = new CV_ImageVideoListAdapter(this, this.cv_myImages);
        this.cv_imageListAdapter = cV_ImageVideoListAdapter;
        this.cv_videoList.setAdapter(cV_ImageVideoListAdapter);
        this.cv_ic_lock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ImageVideoListActivity.cv_checkpauseoperation_img_video_list = true;
                if (CV_HelperResizer.myList.size() == 0) {
                    Toast.makeText(CV_ImageVideoListActivity.this, "Please Select At Least One Video", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < CV_HelperResizer.myList.size(); i++) {
                    arrayList.add(CV_HelperResizer.myList.get(i).getImageUrl());
                }
                if (CV_VaultOptionMainActivity.cv_image_video == 0) {
                    CV_ImageVideoListActivity cV_ImageVideoListActivity = CV_ImageVideoListActivity.this;
                    cV_ImageVideoListActivity.hideThem(CV_MyHelper.getVideoFolder(cV_ImageVideoListActivity.cv_mContext), arrayList);
                } else {
                    CV_ImageVideoListActivity cV_ImageVideoListActivity2 = CV_ImageVideoListActivity.this;
                    cV_ImageVideoListActivity2.hideThem(CV_MyHelper.getImagesFolder(cV_ImageVideoListActivity2.cv_mContext), arrayList);
                }
                CV_HelperResizer.myList.clear();
            }
        });
        this.cv_ic_option.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ImageVideoListActivity.this.cv_ShowSelectAllDialog();
            }
        });
        this.cv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ImageVideoListActivity.this.onBackPressed();
            }
        });
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this);
        CV_HelperResizer.setSize(this.cv_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_option, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_iv_view, 50, 50, true);
        CV_HelperResizer.setSize(this.cv_ic_lock, 150, 150, true);
        CV_HelperResizer.setMargin(this.cv_iv_view, 80, 0, 0, 0);
    }

    /* access modifiers changed from: private */
    public void cv_ShowSelectAllDialog() {
        final CV_RelativePopupWindow cV_RelativePopupWindow = new CV_RelativePopupWindow();
        ViewGroup viewGroup = null;
        View inflate = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.cv_select_all_layout, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ic_selectall);
        CV_HelperResizer.setSize(imageView, 290, 100);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_HelperResizer.myList.clear();
                CV_HelperResizer.myList.addAll(CV_ImageVideoListActivity.this.cv_myImages);
                CV_ImageVideoListActivity.this.cv_imageListAdapter.notifyDataSetChanged();
                cV_RelativePopupWindow.dismiss();
            }
        });
        cV_RelativePopupWindow.setContentView(inflate);
        cV_RelativePopupWindow.setWidth(-2);
        cV_RelativePopupWindow.setHeight(-2);
        cV_RelativePopupWindow.setFocusable(true);
        cV_RelativePopupWindow.setOutsideTouchable(true);
        cV_RelativePopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        cV_RelativePopupWindow.showOnAnchor(this.cv_iv_view, 2, 1);
    }

    /* access modifiers changed from: private */
    public void hideThem(String str, ArrayList<String> arrayList) {
        new HideIt(str, arrayList).execute(new Void[0]);
    }

    public class HideIt extends AsyncTask<Void, Void, Void> {
        ArrayList<String> alpath;
        String folpath;

        public HideIt(String str, ArrayList<String> arrayList) {
            this.folpath = str;
            this.alpath = arrayList;
        }

        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            for (int i = 0; i < this.alpath.size(); i++) {
                String str = this.alpath.get(i);
                File file = new File(str);
                File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/", ".Calculator Vault/image");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                File file3 = new File(file2, file.getName());
                try {
                    CV_MyHelper.moveFile(file, file3);
                    CV_MyHelper.insertData(CV_ImageVideoListActivity.this, str, file3.getAbsolutePath());
                    CV_AddPhotoVideoToHideActivity.alhidden.add(file3.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                CV_ImageVideoListActivity cV_ImageVideoListActivity = CV_ImageVideoListActivity.this;
                String[] strArr = {file.getAbsolutePath()};
                String[] strArr2 = null;
                MediaScannerConnection.scanFile(cV_ImageVideoListActivity, strArr, (String[]) null, new MediaScannerConnection.MediaScannerConnectionClient() {
                    public void onMediaScannerConnected() {
                    }

                    public void onScanCompleted(String str, Uri uri) {
                    }
                });
            }
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                CV_ImageVideoListActivity cV_ImageVideoListActivity = CV_ImageVideoListActivity.this;
                cV_ImageVideoListActivity.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (CV_ImageVideoFolderActivity.cv_mActivity != null) {
                CV_ImageVideoFolderActivity.cv_mActivity.finish();
            }
            CV_Adshow.showinterstitialAd(CV_ImageVideoListActivity.this, (Intent) null);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        cv_checkpauseoperation_img_video_list = true;
        CV_HelperResizer.myList.clear();
        finish();
    }

    public void onResume() {
        super.onResume();
        cv_checkpauseoperation_img_video_list = false;
    }

    public void onPause() {
        if (!cv_checkpauseoperation_img_video_list.booleanValue()) {
            CV_HelperResizer.myList.clear();
            startActivity(new Intent(this, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
