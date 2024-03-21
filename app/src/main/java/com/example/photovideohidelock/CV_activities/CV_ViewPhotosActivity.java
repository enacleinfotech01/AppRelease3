package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_KenBurnsView;
import com.example.photovideohidelock.CV_LoopViewPager;
import com.example.photovideohidelock.CV_adapter.CV_ViewPagerAdapter;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class CV_ViewPhotosActivity extends AppCompatActivity {
    LinearLayout cv_btm_bg;
    Boolean cv_checkshare = false;
    File cv_file;
    int cv_for_rotate;
    int cv_for_show = 1;
    ImageView cv_ic_back;
    ImageView cv_ic_play;
    ImageView cv_ic_rotate;
    ImageView cv_ic_share;
    ImageView cv_ic_unlock;
    CV_KenBurnsView cv_kenBurnsView;
    Context cv_mContext;
    int cv_pos;
    int cv_possss;
    TextView cv_tv_title;
    ViewPager cv_viewPager;
    FrameLayout cv_viewPagerFrame;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_view_photos);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_tv_title = (TextView) findViewById(R.id.tv_title);
        this.cv_btm_bg = (LinearLayout) findViewById(R.id.btm_bg);
        this.cv_ic_unlock = (ImageView) findViewById(R.id.ic_unlock);
        this.cv_ic_play = (ImageView) findViewById(R.id.ic_play);
        this.cv_ic_rotate = (ImageView) findViewById(R.id.ic_rotate);
        this.cv_ic_share = (ImageView) findViewById(R.id.ic_share);
        this.cv_kenBurnsView = (CV_KenBurnsView) findViewById(R.id.ken_burns_view);
        this.cv_viewPagerFrame = (FrameLayout) findViewById(R.id.view_pager_frame);
        this.cv_for_rotate = 0;
        this.cv_viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.cv_pos = getIntent().getIntExtra("pos", 0);
        TextView textView = this.cv_tv_title;
        textView.setText("" + (this.cv_pos + 1) + "/" + CV_AddPhotoVideoToHideActivity.alhidden.size());
        this.cv_viewPager.setAdapter(new CV_ViewPagerAdapter(this));
        this.cv_viewPager.setCurrentItem(this.cv_pos);
        this.cv_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                CV_ViewPhotosActivity cV_ViewPhotosActivity = CV_ViewPhotosActivity.this;
                cV_ViewPhotosActivity.cv_pos = i;
                TextView textView = cV_ViewPhotosActivity.cv_tv_title;
                textView.setText("" + (i + 1) + "/" + CV_AddPhotoVideoToHideActivity.alhidden.size());
            }
        });
        this.cv_ic_unlock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ViewPhotosActivity.this.cv_checkshare = true;
                new UnHideIt("", CV_AddPhotoVideoToHideActivity.alhidden.get(CV_ViewPhotosActivity.this.cv_viewPager.getCurrentItem()), true).execute(new Void[0]);
            }
        });
        this.cv_ic_play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ViewPhotosActivity.this.cv_viewPager.setVisibility(View.GONE);
                if (CV_ViewPhotosActivity.this.cv_for_show == 0) {
                    CV_ViewPhotosActivity.this.cv_for_show = 1;
                } else {
                    CV_ViewPhotosActivity.this.cv_for_show = 0;
                }
                CV_ViewPhotosActivity.this.cv_SetSlideShow();
            }
        });
        this.cv_ic_rotate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setInterpolator(new DecelerateInterpolator());
                animationSet.setFillAfter(true);
                animationSet.setFillEnabled(true);
                if (CV_ViewPhotosActivity.this.cv_for_rotate == 0) {
                    CV_ViewPhotosActivity.this.cv_for_rotate = 1;
                    RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 90.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setDuration(1500);
                    rotateAnimation.setFillAfter(true);
                    animationSet.addAnimation(rotateAnimation);
                    CV_ViewPhotosActivity.this.cv_viewPager.startAnimation(animationSet);
                } else if (CV_ViewPhotosActivity.this.cv_for_rotate == 1) {
                    CV_ViewPhotosActivity.this.cv_for_rotate = 2;
                    RotateAnimation rotateAnimation2 = new RotateAnimation(90.0f, 180.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation2.setDuration(1500);
                    rotateAnimation2.setFillAfter(true);
                    animationSet.addAnimation(rotateAnimation2);
                    CV_ViewPhotosActivity.this.cv_viewPager.startAnimation(animationSet);
                } else if (CV_ViewPhotosActivity.this.cv_for_rotate == 2) {
                    CV_ViewPhotosActivity.this.cv_for_rotate = 3;
                    RotateAnimation rotateAnimation3 = new RotateAnimation(180.0f, 270.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation3.setDuration(1500);
                    rotateAnimation3.setFillAfter(true);
                    animationSet.addAnimation(rotateAnimation3);
                    CV_ViewPhotosActivity.this.cv_viewPager.startAnimation(animationSet);
                } else if (CV_ViewPhotosActivity.this.cv_for_rotate == 3) {
                    CV_ViewPhotosActivity.this.cv_for_rotate = 0;
                    RotateAnimation rotateAnimation4 = new RotateAnimation(270.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation4.setDuration(1500);
                    rotateAnimation4.setFillAfter(true);
                    animationSet.addAnimation(rotateAnimation4);
                    CV_ViewPhotosActivity.this.cv_viewPager.startAnimation(animationSet);
                }
            }
        });
        this.cv_ic_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CV_ViewPhotosActivity.this.cv_checkshare = true;
                    CV_ViewPhotosActivity cV_ViewPhotosActivity = CV_ViewPhotosActivity.this;
                    cV_ViewPhotosActivity.cv_shareBitmap(cV_ViewPhotosActivity.cv_screenShot(cV_ViewPhotosActivity.cv_viewPager), "myimage");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ViewPhotosActivity.this.onBackPressed();
            }
        });
    }

    public Bitmap cv_screenShot(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: private */
    public void cv_shareBitmap(Bitmap bitmap, String str) {
        try {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(externalStoragePublicDirectory, str + ".png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            file.setReadable(true, false);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_unlock, 100, 100, true);
        CV_HelperResizer.setSize(this.cv_ic_play, 100, 100, true);
        CV_HelperResizer.setSize(this.cv_ic_rotate, 100, 100, true);
        CV_HelperResizer.setSize(this.cv_ic_share, 100, 100, true);
        CV_HelperResizer.setHeight(1080);
        CV_HelperResizer.setHeight(this.cv_mContext, this.cv_btm_bg, 150);
    }

    /* access modifiers changed from: private */
    public void cv_SetSlideShow() {
        this.cv_kenBurnsView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.cv_kenBurnsView.setSwapMs(3750);
        this.cv_kenBurnsView.setFadeInOutMs(750);
        if (this.cv_for_show == 1) {
            this.cv_ic_rotate.setClickable(true);
            this.cv_viewPagerFrame.removeAllViews();
            this.cv_kenBurnsView.stopKenBurnsAnimation();
            this.cv_kenBurnsView.setVisibility(View.GONE);
            this.cv_viewPagerFrame.setVisibility(View.GONE);
            this.cv_viewPager.setVisibility(View.VISIBLE);
            this.cv_ic_play.setImageResource(R.drawable.cv_play_button);
            this.cv_tv_title.setText("" + (this.cv_pos + 1) + "/" + CV_AddPhotoVideoToHideActivity.alhidden.size());
            return;
        }
        this.cv_ic_rotate.setClickable(false);
        this.cv_viewPager.setVisibility(View.GONE);
        this.cv_ic_play.setImageResource(R.drawable.cv_pause_button);
        ArrayList arrayList = new ArrayList(CV_AddPhotoVideoToHideActivity.alhidden.subList(this.cv_pos, CV_AddPhotoVideoToHideActivity.alhidden.size()));
        for (int i = 0; i < CV_AddPhotoVideoToHideActivity.alhidden.size(); i++) {
            if (!arrayList.contains(CV_AddPhotoVideoToHideActivity.alhidden.get(i))) {
                arrayList.add(CV_AddPhotoVideoToHideActivity.alhidden.get(i));
            }
        }
        this.cv_kenBurnsView.loadStrings(arrayList);
        this.cv_kenBurnsView.setVisibility(View.VISIBLE);
        this.cv_viewPagerFrame.setVisibility(View.VISIBLE);
        CV_LoopViewPager cV_LoopViewPager = new CV_LoopViewPager(this, CV_AddPhotoVideoToHideActivity.alhidden.size(), new CV_LoopViewPager.LoopViewPagerListener() {
            public void onPageScroll(int i, float f, int i2) {
            }

            public void onPageScrollChanged(int i) {
            }

            public void onPageSelected(int i) {
            }

            public View OnInstantiateItem(int i) {
                TextView textView = new TextView(CV_ViewPhotosActivity.this.getApplicationContext());
                textView.setText(String.valueOf(i));
                textView.setVisibility(View.GONE);
                CV_ViewPhotosActivity.this.cv_kenBurnsView.getPOs();
                return textView;
            }
        });
        cV_LoopViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                CV_AddPhotoVideoToHideActivity.alhidden.size();
            }
        });
        this.cv_tv_title.setText("Slide Show");
        cV_LoopViewPager.setCurrentItem(this.cv_pos);
        this.cv_viewPagerFrame.addView(cV_LoopViewPager);
        this.cv_kenBurnsView.setPager(cV_LoopViewPager);
    }

    public class UnHideIt extends AsyncTask<Void, Void, Void> {
        String alpath;
        String folpath;
        boolean toOriginal;

        public UnHideIt(String str, String str2, boolean z) {
            this.folpath = str;
            this.alpath = str2;
            this.toOriginal = z;
        }

        public void onPreExecute() {
            super.onPreExecute();
            CV_MyHelper.showLog("AAA", "Unhiding Starts : ");
        }

        public Void doInBackground(Void... voidArr) {
            String str = this.alpath;
            File file = new File(str);
            CV_ViewPhotosActivity cV_ViewPhotosActivity = CV_ViewPhotosActivity.this;
            cV_ViewPhotosActivity.cv_file = new File(CV_MyHelper.getOriginalPath(cV_ViewPhotosActivity.cv_mContext, str));
            try {
                CV_MyHelper.moveFile(file, CV_ViewPhotosActivity.this.cv_file);
                CV_MyHelper.deleteData(CV_ViewPhotosActivity.this.cv_mContext, str);
                CV_AddPhotoVideoToHideActivity.alhidden.remove(file.getAbsolutePath());
                CV_HelperResizer.myList.remove(CV_ViewPhotosActivity.this.cv_viewPager.getCurrentItem());
            } catch (Exception e) {
                e.printStackTrace();
                CV_MyHelper.showLog("AAA", "Unhiding Error For : " + str);
                CV_MyHelper.showLog("AAA", e.toString());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            CV_MyHelper.showLog("AAA", "Unhiding done for : " + str);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            CV_ViewPhotosActivity cV_ViewPhotosActivity = CV_ViewPhotosActivity.this;
            MediaScannerConnection.scanFile(cV_ViewPhotosActivity, new String[]{cV_ViewPhotosActivity.cv_file.getAbsolutePath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                }
            });
            try {
                CV_ViewPhotosActivity cV_ViewPhotosActivity2 = CV_ViewPhotosActivity.this;
                cV_ViewPhotosActivity2.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            CV_Adshow.showinterstitialAd(CV_ViewPhotosActivity.this, (Intent) null);
            Toast.makeText(CV_ViewPhotosActivity.this.cv_mContext, "Image Unhided Successfully", Toast.LENGTH_SHORT).show();
            CV_MyHelper.showLog("AAA", "Unhiding Ends");
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_checkshare = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        if (this.cv_checkshare.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
            return;
        }
        this.cv_checkshare = false;
    }

    public void onPause() {
        if (!this.cv_checkshare.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
