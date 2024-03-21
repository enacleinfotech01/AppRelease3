package com.example.photovideohidelock.CV_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.example.photovideohidelock.CV_activities.CV_AddPhotoVideoToHideActivity;
import com.jsibbold.zoomage.ZoomageView;

public class CV_ViewPagerAdapter extends PagerAdapter {
    private Context cv_context;
    private LayoutInflater layoutInflater;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CV_ViewPagerAdapter(Context context) {
        this.cv_context = context;
    }

    public int getCount() {
        return CV_AddPhotoVideoToHideActivity.alhidden.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ZoomageView zoomageView = new ZoomageView(this.cv_context);
        Glide.with(this.cv_context).load(CV_AddPhotoVideoToHideActivity.alhidden.get(i)).into((ImageView) zoomageView);
        ((ViewPager) viewGroup).addView(zoomageView, 0);
        return zoomageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((ImageView) obj);
    }
}
