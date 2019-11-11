package com.fearlessbear.uiexperimentproject.motionEventDispatch;

import com.fearlessbear.uiexperimentproject._base.BaseActivity;

public class MotionEventDispatchListActivity extends BaseActivity {

    {
        mTitle = new String[]{
                "上下重叠的两个View，是否同时被认为是TouchTarget",
        };
        mClasses = new Class[]{
                TouchTarget1Activity.class,
        };
    }
}
