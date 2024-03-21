package com.example.photovideohidelock;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.firebase.messaging.ServiceStarter;
import java.util.List;
import java.util.Random;

public class CV_KenBurnsView extends FrameLayout {
    private static final int FIRST_IMAGE_VIEW_INDEX = 0;
    private static final int NUM_OF_IMAGE_VIEWS = 3;
    private static final String PROPERTY_ALPHA = "alpha";
    private static final int SECOND_IMAGE_VIEW_INDEX = 1;
    private static final int THIRD_IMAGE_VIEW_INDEX = 2;
    private static int sCachedSizeForLoadType;
    private int mActiveImageIndex;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mFadeInOutMs;
    private Runnable mForceSwapImageRunnable;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private ImageView[] mImageViews;
    private LoadType mLoadType;
    private CV_LoopViewPager mLoopViewPager;
    private float mMaxScaleFactor;
    private float mMinScaleFactor;
    private List<Object> mMixingList;
    public int mPosition;
    private int mPreviousPosition;
    private final Random mRandom;
    private List<Integer> mResourceIDs;
    private FrameLayout mRootLayout;
    private ImageView.ScaleType mScaleType;
    private List<String> mStrings;
    /* access modifiers changed from: private */
    public Runnable mSwapImageRunnable;
    /* access modifiers changed from: private */
    public int mSwapMs;

    public enum LoadType {
        String,
        ResourceID,
        MIXING
    }
    private int swapDirection(int i, boolean z) {
        if (i != 0) {
            if (i == 1) {
                if (!z) {
                    return 2;
                }
            } else if (i == 2) {
            }
            return 0;
        } else if (z) {
            return 2;
        }
        return 1;
    }
//    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
//        if (r5 != false) goto L_0x0007;
//     */
//    /* Code decompiled incorrectly, please refer to instructions dump. */
//    private int swapDirection(int r4, boolean r5) {
//        /*
//            r3 = this;
//            r0 = 2
//            r1 = 1
//            if (r4 != 0) goto L_0x0009
//            if (r5 == 0) goto L_0x0007
//            goto L_0x0014
//        L_0x0007:
//            r0 = r1
//            goto L_0x0014
//        L_0x0009:
//            r2 = 0
//            if (r4 != r1) goto L_0x000f
//            if (r5 == 0) goto L_0x0014
//            goto L_0x0013
//        L_0x000f:
//            if (r4 != r0) goto L_0x0013
//            if (r5 != 0) goto L_0x0007
//        L_0x0013:
//            r0 = r2
//        L_0x0014:
//            return r0
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.example.photovideohidelock.CV_KenBurnsView.swapDirection(int, boolean):int");
//    }

    public CV_KenBurnsView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CV_KenBurnsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CV_KenBurnsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLoadType = LoadType.String;
        this.mRandom = new Random();
        this.mSwapMs = 5500;
        this.mFadeInOutMs = ServiceStarter.ERROR_UNKNOWN;
        this.mMaxScaleFactor = 1.5f;
        this.mMinScaleFactor = 1.0f;
        this.mPosition = 0;
        this.mPreviousPosition = 0;
        this.mActiveImageIndex = -1;
        this.mScaleType = null;
        this.mSwapImageRunnable = new Runnable() {
            public void run() {
                CV_KenBurnsView.this.autoSwapImage();
                CV_KenBurnsView.this.mHandler.postDelayed(CV_KenBurnsView.this.mSwapImageRunnable, (long) (CV_KenBurnsView.this.mSwapMs - (CV_KenBurnsView.this.mFadeInOutMs * 2)));
            }
        };
        this.mForceSwapImageRunnable = new Runnable() {
            public void run() {
                CV_KenBurnsView.this.forceSwapImage();
                CV_KenBurnsView.this.mHandler.postDelayed(CV_KenBurnsView.this.mSwapImageRunnable, (long) (CV_KenBurnsView.this.mSwapMs - (CV_KenBurnsView.this.mFadeInOutMs * 2)));
            }
        };
        this.mHandler = new Handler();
        this.mContext = context;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public void setPager(CV_LoopViewPager cV_LoopViewPager) {
        this.mLoopViewPager = cV_LoopViewPager;
    }

    public int getPOs() {
        return this.mLoopViewPager.getCurrentItem();
    }

    public void forceSelected(int i) {
        this.mPreviousPosition = this.mPosition;
        if (this.mHandler != null) {
            stopKenBurnsAnimation();
            startForceKenBurnsAnimation();
        }
        this.mPosition = i;
    }

    public int getForcePosition() {
        return this.mPosition + 1;
    }

    /* access modifiers changed from: private */
    public void forceSwapImage() {
        ImageView[] imageViewArr = this.mImageViews;
        if (imageViewArr != null && imageViewArr.length > 0) {
            int i = this.mActiveImageIndex;
            if (i == -1) {
                this.mActiveImageIndex = 0;
                animate(imageViewArr[0]);
                return;
            }
            if (this.mPreviousPosition >= this.mPosition) {
                this.mActiveImageIndex = swapDirection(i, true);
            } else {
                this.mActiveImageIndex = swapDirection(i, false);
            }
            if (this.mPreviousPosition == 0 && this.mPosition == getSizeByLoadType() - 1) {
                this.mActiveImageIndex = swapDirection(this.mActiveImageIndex, true);
            }
            if (this.mPreviousPosition == getSizeByLoadType() - 1 && this.mPosition == 0) {
                this.mActiveImageIndex = swapDirection(this.mActiveImageIndex, false);
            }
            int i2 = this.mActiveImageIndex;
            ImageView[] imageViewArr2 = this.mImageViews;
            if (i2 >= imageViewArr2.length) {
                this.mActiveImageIndex = 0;
            }
            int i3 = this.mActiveImageIndex;
            ImageView imageView = imageViewArr2[i3];
            loadImages(this.mPosition, i3);
            imageView.setAlpha(0.0f);
            ImageView imageView2 = this.mImageViews[i];
            animate(imageView);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration((long) this.mFadeInOutMs);
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView2, "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, 1.0f})});
            animatorSet.start();
        }
    }

    /* access modifiers changed from: private */
    public void autoSwapImage() {
        ImageView[] imageViewArr = this.mImageViews;
        if (imageViewArr != null && imageViewArr.length > 0) {
            int i = this.mActiveImageIndex;
            if (i == -1) {
                this.mActiveImageIndex = 0;
                animate(imageViewArr[0]);
                return;
            }
            int i2 = i + 1;
            this.mActiveImageIndex = i2;
            if (i2 >= imageViewArr.length) {
                this.mActiveImageIndex = 0;
            }
            if (this.mLoopViewPager != null) {
                int i3 = this.mPosition + 1;
                this.mPosition = i3;
                if (i3 >= getSizeByLoadType()) {
                    this.mPosition = 0;
                }
                this.mLoopViewPager.setCurrentItemAfterCancelListener(this.mPosition, false);
            }
            ImageView[] imageViewArr2 = this.mImageViews;
            int i4 = this.mActiveImageIndex;
            ImageView imageView = imageViewArr2[i4];
            loadImages(this.mPosition, i4);
            imageView.setAlpha(0.0f);
            ImageView imageView2 = this.mImageViews[i];
            animate(imageView);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration((long) this.mFadeInOutMs);
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView2, "alpha", new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, 1.0f})});
            animatorSet.start();
        }
    }

    private void start(View view, long j, float f, float f2, float f3, float f4, float f5, float f6) {
        view.setScaleX(f);
        view.setScaleY(f);
        view.setTranslationX(f3);
        view.setTranslationY(f4);
        view.animate().translationX(f5).translationY(f6).scaleX(f2).scaleY(f2).setDuration(j).start();
    }

    private float pickScale() {
        return this.mMinScaleFactor + (this.mRandom.nextFloat() * (this.mMaxScaleFactor - this.mMinScaleFactor));
    }

    private float pickTranslation(int i, float f) {
        return ((float) i) * (f - 1.0f) * (this.mRandom.nextFloat() - 0.5f);
    }

    public void animate(ImageView imageView) {
        float pickScale = pickScale();
        float pickScale2 = pickScale();
        start(imageView, (long) this.mSwapMs, pickScale, pickScale2, pickTranslation(imageView.getWidth(), pickScale), pickTranslation(imageView.getHeight(), pickScale), pickTranslation(imageView.getWidth(), pickScale2), pickTranslation(imageView.getHeight(), pickScale2));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startKenBurnsAnimation();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopKenBurnsAnimation();
    }

    public void stopKenBurnsAnimation() {
        this.mHandler.removeCallbacks(this.mSwapImageRunnable);
        this.mHandler.removeCallbacks(this.mForceSwapImageRunnable);
    }

    public void startKenBurnsAnimation() {
        this.mHandler.post(this.mSwapImageRunnable);
    }

    public void startForceKenBurnsAnimation() {
        this.mHandler.post(this.mForceSwapImageRunnable);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRootLayout = (FrameLayout) inflate(getContext(), R.layout.cv_ken_burns_view, this).findViewById(R.id.ken_burns_root);
    }

    public void loadStrings(List<String> list) {
        this.mLoadType = LoadType.String;
        sCachedSizeForLoadType = 0;
        this.mStrings = list;
        FrameLayout frameLayout = this.mRootLayout;
        if (frameLayout != null) {
            initImageViews(frameLayout);
        }
    }

    public void loadResourceIDs(List<Integer> list) {
        this.mLoadType = LoadType.ResourceID;
        sCachedSizeForLoadType = 0;
        this.mResourceIDs = list;
        FrameLayout frameLayout = this.mRootLayout;
        if (frameLayout != null) {
            initImageViews(frameLayout);
        }
    }

    public void loadMixing(List<Object> list) {
        this.mLoadType = LoadType.MIXING;
        sCachedSizeForLoadType = 0;
        this.mMixingList = list;
        FrameLayout frameLayout = this.mRootLayout;
        if (frameLayout != null) {
            initImageViews(frameLayout);
        }
    }

    private void initImageViews(FrameLayout frameLayout) {
        this.mImageViews = new ImageView[3];
        for (int i = 2; i >= 0; i--) {
            this.mImageViews[i] = new ImageView(this.mContext);
            if (i != 0) {
                this.mImageViews[i].setAlpha(0.0f);
            }
            ImageView.ScaleType scaleType = this.mScaleType;
            if (scaleType != null) {
                this.mImageViews[i].setScaleType(scaleType);
            }
            this.mImageViews[i].setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            frameLayout.addView(this.mImageViews[i]);
        }
        loadImages(0, 0);
    }

    private void loadImages(int i, int i2) {
        loadImage(i, i2);
        int i3 = i - 1;
        int i4 = i + 1;
        if (i3 < 0) {
            i3 = getSizeByLoadType() - 1;
        }
        if (i4 > getSizeByLoadType() - 1) {
            i4 = 0;
        }
        if (i2 == 0) {
            if (i != i3) {
                loadImage(i3, 2);
            }
            if (i != i4) {
                loadImage(i4, 1);
            }
        } else if (i2 == 1) {
            if (i != i3) {
                loadImage(i3, 0);
            }
            if (i != i4) {
                loadImage(i4, 2);
            }
        } else if (i2 == 2) {
            if (i != i3) {
                loadImage(i3, 1);
            }
            if (i != i4) {
                loadImage(i4, 0);
            }
        }
    }

    private int getSizeByLoadType() {
        int i = sCachedSizeForLoadType;
        if (i > 0) {
            return i;
        }
        int i2 = AnonymousClass3.$SwitchMap$com$goka$kenburnsview$KenBurnsView$LoadType[this.mLoadType.ordinal()];
        if (i2 == 1) {
            sCachedSizeForLoadType = this.mStrings.size();
        } else if (i2 == 2) {
            sCachedSizeForLoadType = this.mResourceIDs.size();
        } else if (i2 == 3) {
            sCachedSizeForLoadType = this.mMixingList.size();
        }
        return sCachedSizeForLoadType;
    }

    public static class AnonymousClass3 {
        static final int[] $SwitchMap$com$goka$kenburnsview$KenBurnsView$LoadType;

        static {
            int[] iArr = new int[LoadType.values().length];
            $SwitchMap$com$goka$kenburnsview$KenBurnsView$LoadType = iArr;
            iArr[LoadType.String.ordinal()] = 1;
            iArr[LoadType.ResourceID.ordinal()] = 2;
            try {
                iArr[LoadType.MIXING.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void loadImage(int i, int i2) {
        int i3 = AnonymousClass3.$SwitchMap$com$goka$kenburnsview$KenBurnsView$LoadType[this.mLoadType.ordinal()];
        if (i3 == 1) {
            Glide.with(this.mContext).load(this.mStrings.get(i)).into(this.mImageViews[i2]);
        } else if (i3 == 2) {
            Glide.with(this.mContext).load(this.mResourceIDs.get(i)).into(this.mImageViews[i2]);
        } else if (i3 == 3) {
            Object obj = this.mMixingList.get(i);
            if (obj.getClass() == String.class) {
                Glide.with(this.mContext).load((String) obj).into(this.mImageViews[i2]);
            } else if (obj.getClass() == Integer.class) {
                Glide.with(this.mContext).load((Integer) obj).into(this.mImageViews[i2]);
            }
        }
    }

    public void setSwapMs(int i) {
        this.mSwapMs = i;
    }

    public void setFadeInOutMs(int i) {
        this.mFadeInOutMs = i;
    }

    public void setMaxScaleFactor(float f) {
        this.mMaxScaleFactor = f;
    }

    public void setMinScaleFactor(float f) {
        this.mMinScaleFactor = f;
    }
}
