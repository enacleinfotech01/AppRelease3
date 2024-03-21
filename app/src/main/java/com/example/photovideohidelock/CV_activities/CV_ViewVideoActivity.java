package com.example.photovideohidelock.CV_activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_hidingUtils.CV_MyHelper;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;

import java.io.File;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class CV_ViewVideoActivity extends AppCompatActivity {
    Boolean cv_check_pause_op = false;
    int cv_duration = 0;
    File cv_file;
    Handler cv_handler = new Handler();
    ImageView cv_ic_back;
    ImageView cv_ic_backward;
    ImageView cv_ic_forward;
    ImageView cv_ic_play;
    ImageView cv_ic_unlock;
    Context cv_mContext;
    int cv_pos;
    Runnable cv_seekrunnable = new Runnable() {
        public void run() {
            if (CV_ViewVideoActivity.this.cv_videoview != null && CV_ViewVideoActivity.this.cv_videoview.isPlaying()) {
                int currentPosition = CV_ViewVideoActivity.this.cv_videoview.getCurrentPosition();
                CV_ViewVideoActivity.this.cv_video_seekbar.setProgress(currentPosition);
                try {
                    TextView textView = CV_ViewVideoActivity.this.cv_tv_start;
                    textView.setText("" + CV_ViewVideoActivity.formatTimeUnit((long) currentPosition));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                CV_ViewVideoActivity.this.cv_handler.postDelayed(CV_ViewVideoActivity.this.cv_seekrunnable, 50);
            }
        }
    };
    TextView cv_tv_end;
    TextView cv_tv_start;
    SeekBar cv_video_seekbar;
    VideoView cv_videoview;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cv_activity_view_video);
        this.cv_mContext = this;
//        new AdAdmob(this).BannerAd((RelativeLayout) findViewById(R.id.banner), this);
        cv_init();
        cv_resize();
    }

    private void cv_init() {
        this.cv_ic_back = (ImageView) findViewById(R.id.ic_back);
        this.cv_videoview = (VideoView) findViewById(R.id.videoview);
        this.cv_video_seekbar = (SeekBar) findViewById(R.id.video_seekbar);
        this.cv_tv_start = (TextView) findViewById(R.id.tv_start);
        this.cv_tv_end = (TextView) findViewById(R.id.tv_end);
        this.cv_ic_backward = (ImageView) findViewById(R.id.ic_backward);
        this.cv_ic_play = (ImageView) findViewById(R.id.ic_play);
        this.cv_ic_forward = (ImageView) findViewById(R.id.ic_forward);
        this.cv_ic_unlock = (ImageView) findViewById(R.id.ic_unlock);
        this.cv_pos = getIntent().getIntExtra("pos", 0);
        this.cv_video_seekbar.setEnabled(false);
        this.cv_ic_play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_ViewVideoActivity.this.cv_videoview != null) {
                    if (CV_ViewVideoActivity.this.cv_videoview.isPlaying()) {
                        CV_ViewVideoActivity.this.cv_videoview.pause();
                        CV_ViewVideoActivity.this.cv_ic_play.setImageResource(R.drawable.cv_play_video_button);
                        CV_ViewVideoActivity.this.cv_handler.removeCallbacks(CV_ViewVideoActivity.this.cv_seekrunnable);
                        return;
                    }
                    CV_ViewVideoActivity.this.cv_videoview.start();
                    CV_ViewVideoActivity.this.cv_ic_play.setImageResource(R.drawable.cv_pause_video_button);
                    CV_ViewVideoActivity.this.cv_handler.postDelayed(CV_ViewVideoActivity.this.cv_seekrunnable, 50);
                }
            }
        });
        this.cv_ic_unlock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ViewVideoActivity.this.cv_check_pause_op = true;
                if (CV_ViewVideoActivity.this.cv_videoview != null && CV_ViewVideoActivity.this.cv_videoview.isPlaying()) {
                    CV_ViewVideoActivity.this.cv_videoview.pause();
                    CV_ViewVideoActivity.this.cv_ic_play.setImageResource(R.drawable.cv_play_video_button);
                    CV_ViewVideoActivity.this.cv_handler.removeCallbacks(CV_ViewVideoActivity.this.cv_seekrunnable);
                }
                new UnHideIt("", CV_AddPhotoVideoToHideActivity.alhidden.get(CV_ViewVideoActivity.this.cv_pos), true).execute(new Void[0]);
            }
        });
        this.cv_ic_forward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_ViewVideoActivity.this.cv_pos + 1 < CV_AddPhotoVideoToHideActivity.alhidden.size()) {
                    CV_ViewVideoActivity.this.cv_pos++;
                    CV_ViewVideoActivity.this.cv_initVideoView();
                    return;
                }
                Toast.makeText(CV_ViewVideoActivity.this.cv_mContext, "This is the last video", Toast.LENGTH_LONG).show();
            }
        });
        this.cv_ic_backward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CV_ViewVideoActivity.this.cv_pos - 1 >= 0) {
                    CV_ViewVideoActivity.this.cv_pos--;
                    CV_ViewVideoActivity.this.cv_initVideoView();
                    return;
                }
                Toast.makeText(CV_ViewVideoActivity.this.cv_mContext, "This is the first video", Toast.LENGTH_LONG).show();
            }
        });
        this.cv_ic_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ViewVideoActivity.this.onBackPressed();
            }
        });
        cv_initVideoView();
    }

    private void cv_resize() {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(this.cv_ic_back, 60, 60, true);
        CV_HelperResizer.setSize(this.cv_ic_backward, 120, 120, true);
        CV_HelperResizer.setSize(this.cv_ic_forward, 120, 120, true);
        CV_HelperResizer.setSize(this.cv_ic_play, 160, 160, true);
        CV_HelperResizer.setSize(this.cv_ic_unlock, 80, 80, true);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.cv_check_pause_op = true;
        finish();
    }

    public void onResume() {
        super.onResume();
        this.cv_check_pause_op = false;
    }

    /* access modifiers changed from: private */
    public void cv_initVideoView() {
        this.cv_videoview.setVideoPath(CV_AddPhotoVideoToHideActivity.alhidden.get(this.cv_pos));
        this.cv_videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                CV_ViewVideoActivity.this.cv_videoview.seekTo(CV_ViewVideoActivity.this.cv_videoview.getCurrentPosition());
                CV_ViewVideoActivity.this.cv_ic_play.setImageResource(R.drawable.cv_pause_video_button);
                CV_ViewVideoActivity.this.cv_videoview.start();
                CV_ViewVideoActivity.this.cv_video_seekbar.setProgress(0);
                CV_ViewVideoActivity.this.cv_tv_start.setText("00:00");
                CV_ViewVideoActivity.this.cv_duration = mediaPlayer.getDuration();
                CV_ViewVideoActivity.this.cv_video_seekbar.setMax(CV_ViewVideoActivity.this.cv_duration);
                try {
                    TextView textView = CV_ViewVideoActivity.this.cv_tv_end;
                    textView.setText("" + CV_ViewVideoActivity.formatTimeUnit((long) CV_ViewVideoActivity.this.cv_duration));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CV_ViewVideoActivity.this.cv_handler.postDelayed(CV_ViewVideoActivity.this.cv_seekrunnable, 50);
            }
        });
        this.cv_videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Toast.makeText(CV_ViewVideoActivity.this.getApplicationContext(), "Video Player Supporting issue.", Toast.LENGTH_SHORT).show();
                try {
                    CV_ViewVideoActivity.this.cv_handler.removeCallbacks(CV_ViewVideoActivity.this.cv_seekrunnable);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        });
        this.cv_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                CV_ViewVideoActivity.this.cv_ic_play.setImageResource(R.drawable.cv_pause_video_button);
                CV_ViewVideoActivity.this.cv_videoview.start();
                CV_ViewVideoActivity.this.cv_video_seekbar.setProgress(0);
                CV_ViewVideoActivity.this.cv_tv_start.setText("00:00");
                CV_ViewVideoActivity.this.cv_duration = mediaPlayer.getDuration();
                CV_ViewVideoActivity.this.cv_video_seekbar.setMax(CV_ViewVideoActivity.this.cv_duration);
                try {
                    TextView textView = CV_ViewVideoActivity.this.cv_tv_end;
                    textView.setText("" + CV_ViewVideoActivity.formatTimeUnit((long) CV_ViewVideoActivity.this.cv_duration));
                } catch (Exception unused) {
                }
                CV_ViewVideoActivity.this.cv_handler.postDelayed(CV_ViewVideoActivity.this.cv_seekrunnable, 50);
            }
        });
    }

    public static String formatTimeUnit(long j) throws ParseException {
        return String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))});
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
            CV_ViewVideoActivity cV_ViewVideoActivity = CV_ViewVideoActivity.this;
            cV_ViewVideoActivity.cv_file = new File(CV_MyHelper.getOriginalPath(cV_ViewVideoActivity.cv_mContext, str));
            try {
                CV_MyHelper.moveFile(file, CV_ViewVideoActivity.this.cv_file);
                CV_MyHelper.deleteData(CV_ViewVideoActivity.this.cv_mContext, str);
                CV_AddPhotoVideoToHideActivity.alhidden.remove(file.getAbsolutePath());
                CV_HelperResizer.myList.remove(CV_ViewVideoActivity.this.cv_pos);
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
            CV_ViewVideoActivity cV_ViewVideoActivity = CV_ViewVideoActivity.this;
            MediaScannerConnection.scanFile(cV_ViewVideoActivity, new String[]{cV_ViewVideoActivity.cv_file.getAbsolutePath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                }
            });
            try {
                CV_ViewVideoActivity cV_ViewVideoActivity2 = CV_ViewVideoActivity.this;
                cV_ViewVideoActivity2.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))));
            } catch (Exception e) {
                e.printStackTrace();
            }
            CV_Adshow.showinterstitialAd(CV_ViewVideoActivity.this, (Intent) null);
            Toast.makeText(CV_ViewVideoActivity.this.cv_mContext, "Video Unhided Successfully", Toast.LENGTH_SHORT).show();
            CV_MyHelper.showLog("AAA", "Unhiding Ends");
        }
    }

    public void onPause() {
        if (!this.cv_check_pause_op.booleanValue()) {
            startActivity(new Intent(this.cv_mContext, CV_CalculatorActivity.class));
            finish();
        }
        super.onPause();
    }
}
