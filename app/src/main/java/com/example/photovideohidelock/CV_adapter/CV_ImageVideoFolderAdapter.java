package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.photovideohidelock.CV_Adshow;
import com.example.photovideohidelock.CV_activities.CV_ImageVideoFolderActivity;
import com.example.photovideohidelock.CV_activities.CV_ImageVideoListActivity;
import com.example.photovideohidelock.CV_activities.CV_VaultOptionMainActivity;
import com.example.photovideohidelock.CV_models.CV_VideoFolderModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import com.itsxtt.patternlock.PatternLockView;
import com.makeramen.roundedimageview.RoundedImageView;
import java.io.File;
import java.util.ArrayList;

public class CV_ImageVideoFolderAdapter extends RecyclerView.Adapter<CV_ImageVideoFolderAdapter.Holder> {
    ArrayList<CV_VideoFolderModel> cv_list;
    Activity cv_mContext;

    public CV_ImageVideoFolderAdapter(Activity activity, ArrayList<CV_VideoFolderModel> arrayList) {
        this.cv_mContext = activity;
        this.cv_list = arrayList;
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_video_folder_items, (ViewGroup) null));
    }

    public void onBindViewHolder(final Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_mainLay2, 465, 480, true);
        CV_HelperResizer.setSize(holder.cv_mainLay, 465, PatternLockView.DEFAULT_ERROR_DURATION, true);
        CV_HelperResizer.setSize(holder.cv_img, 465, PatternLockView.DEFAULT_ERROR_DURATION, true);
        CV_HelperResizer.setSize(holder.cv_ic_play, 100, 100, true);
        CV_HelperResizer.setSize(holder.cv_bottom_lay, 465, 100, true);
        CV_HelperResizer.setMargin(holder.cv_bottom_lay, 0, 10, 0, 0);
        holder.cv_img.setCornerRadius(12.0f);
        holder.cv_foldername.setText(new File(this.cv_list.get(i).getfolder()).getName());
        Glide.with(this.cv_mContext).load(this.cv_list.get(i).getPath().get(0).getImageUrl()).into((ImageView) holder.cv_img);
        TextView textView = holder.cv_vid_count;
        textView.setText(this.cv_list.get(i).getPath().size() + "");
        if (CV_VaultOptionMainActivity.cv_image_video == 0) {
            holder.cv_ic_play.setVisibility(View.VISIBLE);
        } else {
            holder.cv_ic_play.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CV_ImageVideoFolderActivity.cv_checkpauseoperationimg_video = true;
                CV_ImageVideoListActivity.cv_imageFolderData = CV_ImageVideoFolderAdapter.this.cv_list.get(i);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.cv_foldername.setTextColor(-1);
                    }
                }, 100);
                CV_Adshow.showinterstitialAd(CV_ImageVideoFolderAdapter.this.cv_mContext, new Intent(CV_ImageVideoFolderAdapter.this.cv_mContext, CV_ImageVideoListActivity.class));
            }
        });
    }

    public int getItemCount() {
        return this.cv_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public LinearLayout cv_bottom_lay;
        public TextView cv_foldername;
        ImageView cv_ic_play;
        public RoundedImageView cv_img;
        RelativeLayout cv_mainLay;
        RelativeLayout cv_mainLay2;
        public TextView cv_vid_count;

        public Holder(View view) {
            super(view);
            this.cv_mainLay = (RelativeLayout) view.findViewById(R.id.mainLay);
            this.cv_bottom_lay = (LinearLayout) view.findViewById(R.id.bottom_lay);
            this.cv_img = (RoundedImageView) view.findViewById(R.id.img);
            this.cv_foldername = (TextView) view.findViewById(R.id.foldername);
            this.cv_vid_count = (TextView) view.findViewById(R.id.vid_count);
            this.cv_mainLay2 = (RelativeLayout) view.findViewById(R.id.mainLay2);
            this.cv_ic_play = (ImageView) view.findViewById(R.id.ic_play);
        }
    }
}
