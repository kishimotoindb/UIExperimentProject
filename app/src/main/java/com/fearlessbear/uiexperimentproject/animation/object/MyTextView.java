package com.fearlessbear.uiexperimentproject.animation.object;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by cuihaichen on 2020.03.12
 */
public class MyTextView extends TextView {
    private static final String TAG = "MyTextView";
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Trace.beginSection(getText().toString()+"_dispatch_draw");
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: text " + getText());
        Trace.endSection();
    }
}
