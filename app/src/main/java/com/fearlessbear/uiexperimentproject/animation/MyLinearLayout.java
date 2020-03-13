package com.fearlessbear.uiexperimentproject.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by cuihaichen on 2020.03.12
 */
public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Trace.beginSection("linear_dispatch_draw");
        super.dispatchDraw(canvas);
        Log.d(TAG, "dispatchDraw: ");
        Trace.endSection();
    }
}
