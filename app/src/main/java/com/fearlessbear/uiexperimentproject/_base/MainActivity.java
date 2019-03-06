package com.fearlessbear.uiexperimentproject._base;

import com.fearlessbear.uiexperimentproject.draw.DrawListActivity;
import com.fearlessbear.uiexperimentproject.recyclerView.RecyclerActivity;
import com.fearlessbear.uiexperimentproject.textview.InputTypeActivity;

public class MainActivity extends BaseActivity {
    {
        mTitle = new String[]{
                "RecyclerView",
                "TextView etc",
                "绘制相关"
        };
        mClasses = new Class[]{
                RecyclerActivity.class,
                InputTypeActivity.class,
                DrawListActivity.class
        };
    }
}
