package com.fearlessbear.uiexperimentproject.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by BigFaceBear on 2019.03.06
 */
public class CanvasClipView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF mRectF = new RectF();
    RectF mTotalArea = new RectF();
    int mColor;
    Path mPath = new Path();

    public CanvasClipView(Context context) {
        super(context);
    }

    public CanvasClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mColor = getResources().getColor(android.R.color.black);
        mPaint.setColor(mColor);
        mRectF.left = 50;
        mRectF.right = 200;
        mRectF.top = 50;
        mRectF.bottom = 400;
        mPath.addRoundRect(mRectF, 10, 10, Path.Direction.CW);



    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int save = canvas.save();

        if (Build.VERSION.SDK_INT >= 26) {
//            canvas.clipOutPath(mPath);
        } else {
            canvas.clipPath(mPath);
            mTotalArea.left = getLeft();
            mTotalArea.right = getRight();
            mTotalArea.top = getTop();
            mTotalArea.bottom = getBottom();
            canvas.clipRect(mTotalArea, Region.Op.XOR);
        }

        canvas.drawColor(mColor);
        canvas.restoreToCount(save);

    }
}
