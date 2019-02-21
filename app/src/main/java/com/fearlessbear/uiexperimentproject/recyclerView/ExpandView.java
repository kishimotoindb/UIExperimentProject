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

public class ExpandView extends FrameLayout {
    private static final String TAG = "ExpandedView";

    // ExpandView展开到最大时其上边缘需要在父listView中滑动的总距离，这个距离ExpandedView等于未展开起始状
    // 态下的ExpandedView的top
    private int mExpandViewTotalNeedScrollBeforeExpanded;
    // ExpandView未展开与完全展开两种状态下的高度差
    private int mExpandViewTotalNeedExpandBeforeExpanded;

    // ExpandView展开后，控件在二楼最终显示的位置；px
    private int mTargetViewTargetY = 1174;
    // ExpandView展开前，控件在一楼的初始位置，当前以一楼的getTop作为对齐的目标；px
    private int mTargetViewStartY;

    // 为了让一楼和二楼的控件在达到最终显示位置前有一个交汇，二楼需要在一开始上滑一段距离。这个距离是：
    // （控件最终显示位置 - 起始显示位置的差）* 百分比
    private float mSecondFloorInitScrollOnActionDownPercent = 0.5f;
    // 一楼和二楼目标控件的交汇点
    private float mTargetViewIntersectionPointY;

    // 手指坐标
    // 手指滑动这么长距离后，二楼完全展开
    private int mFingerTotalNeedMoveBeforeExpanded;
    private float mFingerDownY;
    private float mFingerLastY;

    ListView mParentListView;
    private View mFirstFloor;
    private ListView mSecondFloor;
    private int mFirstFloorOriginalTop;
    private int mFirstFloorOriginalBottom;

    public ExpandView(@NonNull Context context) {
        super(context);
    }

    public ExpandView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        mTargetViewTargetY = targetY;
        Log.e(TAG, "setTargetY: " + targetY);
    }

    public void setTargetViewStartY(int startY) {
        mTargetViewStartY = startY;
        Log.e(TAG, "setTargetViewStartY: " + startY);
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
        mFingerDownY = mFingerLastY = event.getRawY();
        mFirstFloorOriginalTop = mExpandViewTotalNeedScrollBeforeExpanded = mTargetViewStartY = getTop();
        mFirstFloorOriginalBottom = getBottom();
        mExpandViewTotalNeedExpandBeforeExpanded = mParentListView.getHeight() - mFirstFloorOriginalBottom + mFirstFloorOriginalTop;
        // 计算需要移动的距离
        mFingerTotalNeedMoveBeforeExpanded = mTargetViewTargetY - mTargetViewStartY;

        mTargetViewIntersectionPointY = mTargetViewTargetY - (mTargetViewTargetY - mTargetViewStartY) * mSecondFloorInitScrollOnActionDownPercent;
        // 一上来首先就需要把二楼的list通过滚到完成如下的视觉效果：
        // 即让list感觉是以屏幕顶部为原点进行显示的。同时，为了有一二楼控件交汇的效果，需要移出屏幕顶部一部分
        int initScroll = (int) (mFirstFloorOriginalTop + (mTargetViewTargetY - mTargetViewStartY) * mSecondFloorInitScrollOnActionDownPercent);
        int scrolled = 0;
        int height = mSecondFloor.getHeight();
        while (scrolled < initScroll) {
            int tmp = (scrolled + height) < initScroll ? height : (initScroll - scrolled);
            mSecondFloor.scrollListBy(tmp);
            scrolled += tmp;
        }
    }

    private void processMove(MotionEvent event) {
        float fingerCurY = event.getRawY();
        final float fingerMovedY = fingerCurY - mFingerDownY;
        if (fingerMovedY < 0 || fingerMovedY > mFingerTotalNeedMoveBeforeExpanded) {
            return;
        }

        // 一、处理ExpandView的扩张

        // 1.计算手指滑动距离的百分比：手指滑动距离/(终点位置-起点位置)
        float percent = fingerMovedY / mFingerTotalNeedMoveBeforeExpanded;

        // 2.根据手指滑动距离百分比确定listView向上滚动的距离
        // 本次将要上滑的目标位置
        float expandViewScrollTargetY = mExpandViewTotalNeedScrollBeforeExpanded * (1 - percent);
        int parentScrollDelta = (int) (getTop() - expandViewScrollTargetY);
        mParentListView.scrollListBy(parentScrollDelta);

        // 3.根据手指滑动距离百分比确定卡片当前的大小（可以证明上滑list和卡片调整高度是两个独立的过程，不会出现
        // 上滑list的过程中，卡片的底部已经超出屏幕底部的情况）
        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.height = (int) (mFirstFloorOriginalBottom - mFirstFloorOriginalTop + mExpandViewTotalNeedExpandBeforeExpanded * percent);
        setLayoutParams(lp);

        // 二、处理一楼的跟手效果

        // 1. 为了达到一楼控件的跟手效果，手指移动了多远，一楼的控件就要一动多远，但是因为同时 parent listView
        // 还在上滑，所以一楼控件相对于屏幕的绝对位移需要补偿一个listView上滑的距离。这里通过一楼的marginTop属
        // 性控制其在父控件中的位置。
        ViewGroup.MarginLayoutParams flp = (MarginLayoutParams) mFirstFloor.getLayoutParams();
        int firstFloorOldTopMargin = flp.topMargin;
        int firstFloorTopMargin = (int) (fingerMovedY + mExpandViewTotalNeedScrollBeforeExpanded * percent);
        flp.topMargin = firstFloorTopMargin;
        mFirstFloor.setLayoutParams(flp);

        // 三、处理二楼的跟随和滑动效果

        // 1.当一楼和二楼到达交汇点之前：二楼需要保持相对于屏幕的绝对位置，所以动态保持这个位置。
        // 2.当交汇之后，二楼list向下滑动露出顶部
        mFirstFloor.getLocationOnScreen(mLocation);
        Log.e(TAG, "first floor y " + mLocation[1]);
        if (mLocation[1] < mTargetViewIntersectionPointY) {
            mSecondFloor.scrollListBy(-parentScrollDelta);
            mSecondFloor.setVisibility(INVISIBLE);
        } else {
            mSecondFloor.scrollListBy(firstFloorOldTopMargin - firstFloorTopMargin);
            mSecondFloor.setVisibility(VISIBLE);
            float secondFloorAlpha = getSecondFloorAlpha();
            Log.i(TAG, "alpha "+secondFloorAlpha);
            mSecondFloor.setAlpha(secondFloorAlpha);
        }
        mFirstFloor.setVisibility(mLocation[1] >= mTargetViewTargetY ? INVISIBLE : VISIBLE);
        mFingerLastY = fingerCurY;
    }

    private float getSecondFloorAlpha() {
        mFirstFloor.getLocationOnScreen(mLocation);
        return (mLocation[1] - mTargetViewIntersectionPointY) / ((mTargetViewTargetY - mTargetViewStartY) * mSecondFloorInitScrollOnActionDownPercent);
    }

    private int[] mLocation = new int[2];
}
