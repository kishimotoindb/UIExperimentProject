package com.fearlessbear.uiexperimentproject.draw.porterduff;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/*
 * Created by cuihaichen on 2020.03.06
 *
 * Clear模式相当于橡皮擦，可以擦除目标图像与path重叠的区域
 */
public class ClearModeExpView extends ImageView {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mClearPath = new Path();

    public ClearModeExpView(Context context) {
        super(context);
    }

    public ClearModeExpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearModeExpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClearModeExpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mClearPath.moveTo(200, 0);
        mClearPath.lineTo(150, 150);
        mClearPath.lineTo(240, 200);
        mClearPath.close();

        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        mPaint.setXfermode(xfermode);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(0, 0, getWidth(), getHeight(), null);
        super.onDraw(canvas);

        canvas.drawPath(mClearPath, mPaint);
        canvas.restore();
    }
}
