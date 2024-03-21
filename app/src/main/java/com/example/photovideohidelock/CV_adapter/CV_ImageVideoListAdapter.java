package com.example.photovideohidelock.CV_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.photovideohidelock.CV_activities.CV_VaultOptionMainActivity;
import com.example.photovideohidelock.CV_models.CV_VideoModel;
import com.example.photovideohidelock.CV_utils.CV_HelperResizer;
import com.example.photovideohidelock.R;
import com.makeramen.roundedimageview.RoundedImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class CV_ImageVideoListAdapter extends RecyclerView.Adapter<CV_ImageVideoListAdapter.Holder> {
    ArrayList<CV_VideoModel> cv_images;
    Context cv_mContext;

    public CV_ImageVideoListAdapter(Context context, ArrayList<CV_VideoModel> arrayList) {
        this.cv_mContext = context;
        this.cv_images = arrayList;
    }

    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = null;
        return new Holder(LayoutInflater.from(this.cv_mContext).inflate(R.layout.cv_video_list_items, (ViewGroup) null));
    }

    public void onBindViewHolder(Holder holder, @SuppressLint("RecyclerView") final int i) {
        CV_HelperResizer.getheightandwidth(this.cv_mContext);
        CV_HelperResizer.setSize(holder.cv_centerLay, 337, 385, true);
        CV_HelperResizer.setSize(holder.cv_ic_play, 100, 100, true);
        CV_HelperResizer.setMargin(holder.cv_mainlay, 0, 15, 0, 0);
        CV_HelperResizer.setSize(holder.cv_ic_done, 100, 100, true);
        final File file = new File(this.cv_images.get(i).getImageUrl());
        holder.cv_foldername.setText(this.cv_images.get(i).getTitle());
        Glide.with(this.cv_mContext).load(this.cv_images.get(i).getImageUrl()).into((ImageView) holder.cv_folder_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = false;
                for (int i = 0; i < CV_HelperResizer.myList.size(); i++) {
                    if (CV_HelperResizer.myList.get(i).getImageUrl().equals(file.getAbsolutePath())) {
                        CV_HelperResizer.myList.remove(i);
                        z = true;
                    }
                }
                if (!z) {
                    CV_HelperResizer.myList.add(0, CV_ImageVideoListAdapter.this.cv_images.get(i));
                }
                CV_ImageVideoListAdapter.this.notifyDataSetChanged();
            }
        });
        Iterator<CV_VideoModel> it = CV_HelperResizer.myList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().getImageUrl().equals(this.cv_images.get(i).getImageUrl())) {
                z = true;
            }
        }
        if (z) {
            holder.cv_selected.setVisibility(View.VISIBLE);
            holder.cv_ic_done.setVisibility(View.VISIBLE);
            holder.cv_ic_play.setVisibility(View.GONE);
        } else {
            holder.cv_selected.setVisibility(View.GONE);
            holder.cv_ic_done.setVisibility(View.GONE);
            holder.cv_ic_play.setVisibility(View.VISIBLE);
        }
        if (CV_VaultOptionMainActivity.cv_image_video == 0) {
            holder.cv_ic_play.setVisibility(View.VISIBLE);
        } else {
            holder.cv_ic_play.setVisibility(View.GONE);
        }
        if (i == this.cv_images.size() - 1) {
            CV_HelperResizer.setMargin(holder.cv_mainlay, 0, 40, 0, 40);
        } else {
            CV_HelperResizer.setMargin(holder.cv_mainlay, 0, 40, 0, 0);
        }
    }

    public int getItemCount() {
        return this.cv_images.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public RelativeLayout cv_centerLay;
        public RoundedImageView cv_folder_img;
        public TextView cv_foldername;
        public ImageView cv_ic_done;
        public ImageView cv_ic_play;
        public LinearLayout cv_mainlay;
        public RoundedImageView cv_selected;

        public Holder(View view) {
            super(view);
            this.cv_mainlay = (LinearLayout) view.findViewById(R.id.mainlay);
            this.cv_folder_img = (RoundedImageView) view.findViewById(R.id.imageviewround);
            this.cv_foldername = (TextView) view.findViewById(R.id.foldername);
            this.cv_centerLay = (RelativeLayout) view.findViewById(R.id.centerLay);
            this.cv_selected = (RoundedImageView) view.findViewById(R.id.selected);
            this.cv_ic_done = (ImageView) view.findViewById(R.id.ic_done);
            this.cv_ic_play = (ImageView) view.findViewById(R.id.ic_play);
            this.cv_folder_img.setCornerRadius(22.0f);
        }
    }
}
