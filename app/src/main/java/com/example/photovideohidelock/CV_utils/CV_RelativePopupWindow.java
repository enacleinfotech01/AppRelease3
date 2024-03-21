package com.example.photovideohidelock.CV_utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import androidx.core.widget.PopupWindowCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CV_RelativePopupWindow extends PopupWindow {
    int i5;
    int i6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizontalPosition {
        public static final int ALIGN_LEFT = 3;
        public static final int ALIGN_RIGHT = 4;
        public static final int CENTER = 0;
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VerticalPosition {
        public static final int ABOVE = 1;
        public static final int ALIGN_BOTTOM = 4;
        public static final int ALIGN_TOP = 3;
        public static final int BELOW = 2;
        public static final int CENTER = 0;
    }

    private static int getDropDownMeasureSpecMode(int i) {
        return i != -2 ? 1073741824 : 0;
    }

    public CV_RelativePopupWindow(Context context) {
        super(context);
    }

    public CV_RelativePopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CV_RelativePopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CV_RelativePopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CV_RelativePopupWindow() {
    }

    public CV_RelativePopupWindow(View view) {
        super(view);
    }

    public CV_RelativePopupWindow(int i, int i2) {
        super(i, i2);
    }

    public CV_RelativePopupWindow(View view, int i, int i2) {
        super(view, i, i2);
    }

    public CV_RelativePopupWindow(View view, int i, int i2, boolean z) {
        super(view, i, i2, z);
    }

    public void showOnAnchor(View view, int i, int i2) {
        Log.e("@@@@@@@@@@@", "ShowMoreDialog: ============123");
        showOnAnchor(view, i, i2, 0, 0);
        Log.e("@@@@@@@@@@@", "ShowMoreDialog: ============123" + view);
        Log.e("@@@@@@@@@@@", "ShowMoreDialog: ============123" + i);
        Log.e("@@@@@@@@@@@", "ShowMoreDialog: ============123" + i2);
    }

    public void showOnAnchor(View view, int i, int i2, boolean z) {
        showOnAnchor(view, i, i2, 0, 0, z);
    }

    public void showOnAnchor(View view, int i, int i2, int i3, int i4) {
        showOnAnchor(view, i, i2, i3, i4, true);
    }

    public void showOnAnchor(View view, int i, int i2, int i3, int i4, boolean z) {
        setClippingEnabled(z);
        View contentView = getContentView();
        contentView.measure(makeDropDownMeasureSpec(getWidth()), makeDropDownMeasureSpec(getHeight()));
        int measuredWidth = contentView.getMeasuredWidth();
        int measuredHeight = contentView.getMeasuredHeight();
        if (!z) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            i3 += iArr[0];
            i4 += iArr[1] + view.getHeight();
        }
        if (i != 0) {
            if (i == 1) {
                measuredHeight += view.getHeight();
            } else if (i == 3) {
                this.i6 = view.getHeight();
            }
            int i7 = i4 - measuredHeight;
            if (i2 != 0) {
                this.i5 = (view.getWidth() / 2) - (measuredWidth / 2);
            } else if (i2 == 1) {
            } else {
                if (i2 != 2) {
                    if (i2 != 3 && i2 == 4) {
                        view.getWidth();
                    }
                    if (!z) {
                        PopupWindowCompat.showAsDropDown(this, view, i3, i7, 17);
                    } else {
                        showAtLocation(view, 0, i3, i7);
                    }
                } else {
                    this.i5 = view.getWidth();
                }
            }
        } else {
            this.i6 = (view.getHeight() / 2) + (measuredHeight / 2);
        }
    }

    private static int makeDropDownMeasureSpec(int i) {
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), getDropDownMeasureSpecMode(i));
    }
}
