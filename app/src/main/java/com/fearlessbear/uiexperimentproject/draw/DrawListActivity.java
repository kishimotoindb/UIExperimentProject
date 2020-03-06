package com.fearlessbear.uiexperimentproject.draw;

import com.fearlessbear.uiexperimentproject._base.BaseActivity;
import com.fearlessbear.uiexperimentproject.recyclerView.RecyclerActivity;
import com.fearlessbear.uiexperimentproject.textview.InputTypeActivity;

public class DrawListActivity extends BaseActivity {
    {
        mTitle = new String[]{
                "画布区域裁减",
                "在View的范围外进行绘制",
                "PorterDuffClear"
        };
        mClasses = new Class[]{
                CanvasClipActivity.class,
                DrawOutsideViewActivity.class,
                PorterDuffActivity.class
        };
    }
}
