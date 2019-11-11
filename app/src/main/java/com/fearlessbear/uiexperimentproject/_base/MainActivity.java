package com.fearlessbear.uiexperimentproject._base;

import com.fearlessbear.uiexperimentproject.animation.AnimationListActivity;
import com.fearlessbear.uiexperimentproject.draw.DrawListActivity;
import com.fearlessbear.uiexperimentproject.motionEventDispatch.MotionEventDispatchListActivity;
import com.fearlessbear.uiexperimentproject.recyclerView.RecyclerActivity;
import com.fearlessbear.uiexperimentproject.textview.InputTypeActivity;
import com.fearlessbear.uiexperimentproject.webview.WebViewActivity;
import com.fearlessbear.uiexperimentproject.window.WindowListActivity;

public class MainActivity extends BaseActivity {
    {
        mTitle = new String[]{
                "RecyclerView",
                "TextView相关",
                "绘制相关",
                "动画相关",
                "WebView相关",
                "Window相关",
                "事件分发相关"
        };
        mClasses = new Class[]{
                RecyclerActivity.class,
                InputTypeActivity.class,
                DrawListActivity.class,
                AnimationListActivity.class,
                WebViewActivity.class,
                WindowListActivity.class,
                MotionEventDispatchListActivity.class
        };
    }
}
