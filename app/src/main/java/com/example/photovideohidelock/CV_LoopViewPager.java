package com.example.photovideohidelock;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class CV_LoopViewPager extends ViewPager {
    private static final int ALL_PAGE_COUNT = 100000;
    /* access modifiers changed from: private */
    public int mAdapterPages;
    /* access modifiers changed from: private */
    public int mCurrentPage;
    private int mFirstPosition;
    /* access modifiers changed from: private */
    public LoopViewPagerListener mListener;
    private LoopOnPageChangeListener mOnPageChangeListener;
    private int mPages;

    public interface LoopViewPagerListener {
        View OnInstantiateItem(int i);

        void onPageScroll(int i, float f, int i2);

        void onPageScrollChanged(int i);

        void onPageSelected(int i);
    }

    public CV_LoopViewPager(Context context, int i, LoopViewPagerListener loopViewPagerListener) {
        super(context);
        this.mPages = i;
        if (i != 0) {
            if (i == 1) {
                this.mAdapterPages = 1;
            } else {
                this.mAdapterPages = ALL_PAGE_COUNT;
            }
            setAdapter(new LoopPagerAdapter());
            int i2 = this.mPages;
            this.mFirstPosition = ((ALL_PAGE_COUNT / i2) / 2) * i2;
            setCurrentItem(-1, false);
            this.mListener = loopViewPagerListener;
            LoopOnPageChangeListener loopOnPageChangeListener = new LoopOnPageChangeListener();
            this.mOnPageChangeListener = loopOnPageChangeListener;
            addOnPageChangeListener(loopOnPageChangeListener);
        }
    }

    public void setCurrentItem(int i, boolean z) {
        super.setCurrentItem(i < 0 ? this.mFirstPosition : i + this.mFirstPosition, z);
    }

    public void setCurrentItemAfterCancelListener(int i, boolean z) {
        removeOnPageChangeListener(this.mOnPageChangeListener);
        super.setCurrentItem(i < 0 ? this.mFirstPosition : i + this.mFirstPosition, z);
        addOnPageChangeListener(this.mOnPageChangeListener);
    }

    public void setCurrentItem(int i) {
        super.setCurrentItem(i < 0 ? this.mFirstPosition : i + this.mFirstPosition);
    }

    public void setCurrentItemAfterCancelListener(int i) {
        removeOnPageChangeListener(this.mOnPageChangeListener);
        super.setCurrentItem(i < 0 ? this.mFirstPosition : i + this.mFirstPosition);
        addOnPageChangeListener(this.mOnPageChangeListener);
    }

    public class LoopOnPageChangeListener implements OnPageChangeListener {
        private LoopOnPageChangeListener() {
        }

        public void onPageScrolled(int i, float f, int i2) {
            CV_LoopViewPager.this.mListener.onPageScroll(i, f, i2);
        }

        public void onPageSelected(int i) {
            CV_LoopViewPager cV_LoopViewPager = CV_LoopViewPager.this;
            int unused = cV_LoopViewPager.mCurrentPage = cV_LoopViewPager.pos2page(i);
            CV_LoopViewPager.this.mListener.onPageSelected(CV_LoopViewPager.this.mCurrentPage);
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                CV_LoopViewPager.this.mListener.onPageScrollChanged(CV_LoopViewPager.this.mCurrentPage);
            }
        }
    }

    /* access modifiers changed from: private */
    public int pos2page(int i) {
        return i % this.mPages;
    }

    private class LoopPagerAdapter extends PagerAdapter {
        private LoopPagerAdapter() {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View OnInstantiateItem = CV_LoopViewPager.this.mListener.OnInstantiateItem(CV_LoopViewPager.this.pos2page(i));
            viewGroup.addView(OnInstantiateItem);
            return OnInstantiateItem;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return CV_LoopViewPager.this.mAdapterPages;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }
    }
}
