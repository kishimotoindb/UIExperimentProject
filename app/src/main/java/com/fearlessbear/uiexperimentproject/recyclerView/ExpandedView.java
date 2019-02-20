package com.fearlessbear.uiexperimentproject.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.fearlessbear.uiexperimentproject.R;

/**
 * Created by BigFaceBear on 2019.02.19
 */
public class ExpandedView extends FrameLayout {
    private static final String TAG = "ExpandedView";

    // 假设手指滑动这么多像素时，二楼完全展开；px
    private int mMoveSpan;
    // 卡片展开后，控件在二楼最终显示的位置；px
    private int mViewTargetY = 1174;
    // 卡片展开前，控件在一楼的初始位置，当前以一楼的getTop作为对齐的目标；px
    private int mViewStartY;
    // 为了让一楼和二楼的控件有一个交汇，二楼初始上滑的距离
    private int mSecondFloorInitScrollY = 300;// px

    // 手指坐标
    private float mStartY;
    private float mLastY;

    ListView mParentListView;
    private View mFirstFloor;
    private ListView mSecondFloor;
    private int mOriginalTop;
    private int mOriginalBottom;
    private int mHeightIncSpan;

    public ExpandedView(@NonNull Context context) {
        super(context);
    }

    public ExpandedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExpandedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFirstFloor = findViewById(R.id.first_floor);
        mSecondFloor = findViewById(R.id.second_floor);
    }

    public void setListView(ListView listView) {
        mParentListView = listView;
    }

    public void setTargetY(int targetY) {
        mViewTargetY = targetY;
        Log.e(TAG, "setTargetY: " + targetY);
    }

    public void setViewStartY(int startY) {
        mViewStartY = startY;
        Log.e(TAG, "setViewStartY: " + startY);
    }

    public ListView getSecondFloor() {
        return mSecondFloor;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(true);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                processDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                processMove(event);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void processDown(MotionEvent event) {
        mStartY = event.getRawY();
        mLastY = event.getRawY();
        mOriginalTop = getTop();
        mViewStartY = mOriginalTop;
        mOriginalBottom = getBottom();
        mHeightIncSpan = getResources().getDisplayMetrics().heightPixels - mOriginalBottom + mOriginalTop;
        // 计算需要移动的距离
        mMoveSpan = mViewTargetY - mViewStartY;

        // 一上来首先就需要把二楼的list通过滚到完成如下的视觉效果：
        // 即让list感觉是以屏幕顶部为原点进行显示的。同时，为了有一二楼控件交汇的效果，需要移出屏幕顶部一部分
        int initScroll = mOriginalTop + mSecondFloorInitScrollY;
        int scrolled = 0;
        int height = mSecondFloor.getHeight();
        while (scrolled < initScroll) {
            int tmp = (scrolled + height) < initScroll ? height : (initScroll - scrolled);
            mSecondFloor.scrollListBy(tmp);
            scrolled += tmp;
        }
    }

    private void processMove(MotionEvent event) {
        float curY = event.getRawY();
        float deltaY = curY - mStartY;
        if (deltaY < 0 || deltaY > mMoveSpan) {
            return;
        }

        // 一、处理卡片的扩张

        // 1.计算手指滑动距离的百分比：手指滑动距离/(终点位置-起点位置)
        float percent = deltaY / mMoveSpan;

        // 2.根据手指滑动距离百分比确定listView向上滚动的距离
        // 本次将要上滑的目标位置
        float curScrollTargetY = mOriginalTop * (1 - percent);
        int parentScrollDelta = (int) (getTop() - curScrollTargetY);
        mParentListView.scrollListBy(parentScrollDelta);

        // 3.根据手指滑动距离百分比确定卡片当前的大小（可以证明上滑list和卡片调整高度是两个独立的过程，不会出现
        // 上滑list的过程中，卡片的底部已经超出屏幕底部的情况）
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = (int) (mOriginalBottom - mOriginalTop + mHeightIncSpan * percent);
        setLayoutParams(lp);

        // 二、处理一楼的跟手效果

        // 1. 为了达到一楼控件的跟手效果，手指移动了多远，一楼的控件就要一动多远，但是因为同时 parent listView
        // 还在上滑，所以一楼控件相对于屏幕的绝对位移需要补偿一个listView上滑的距离。这里通过一楼的marginTop属
        // 性控制其在父控件中的位置。
        ViewGroup.MarginLayoutParams flp = (MarginLayoutParams) mFirstFloor.getLayoutParams();
        flp.topMargin = (int) (deltaY + mOriginalTop * percent);
        mFirstFloor.setLayoutParams(flp);

        // 三、处理二楼的跟随和滑动效果
        // 1.当一楼和二楼到达交汇点之前：二楼需要保持相对于屏幕的绝对位置，所以动态保持这个位置。
        // 2.当交汇之后，二楼list向下滑动露出顶部
        mFirstFloor.getLocationOnScreen(mFirstFloorLocation);
        Log.e(TAG, "first floor y " + mFirstFloorLocation[1]);
        if (mFirstFloorLocation[1] <= mViewTargetY - mSecondFloorInitScrollY) {
            mSecondFloor.scrollListBy(-parentScrollDelta);
            mSecondFloor.setVisibility(INVISIBLE);
            mFirstFloor.setVisibility(VISIBLE);
        } else {
            // 这里在交汇之后，一楼和二楼的下滑速度是不同的。如果需要可以改为相同。
            mSecondFloor.scrollListBy((int) (-parentScrollDelta - (curY - mLastY)));
            mSecondFloor.setVisibility(VISIBLE);
            mFirstFloor.setVisibility(INVISIBLE);
        }
        mLastY = curY;
    }

    private int[] mFirstFloorLocation = new int[2];
}
