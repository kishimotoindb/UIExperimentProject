package com.fearlessbear.uiexperimentproject.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.fearlessbear.uiexperimentproject.R;

/**
 * Created by BigFaceBear on 2019.06.03
 */
public class DrawOutsideView extends ViewGroup {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawOutsideView(Context context) {
        super(context);
    }

    public DrawOutsideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public DrawOutsideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(0, 0, 200, mPaint);
    }
}
