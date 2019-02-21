package com.fearlessbear.uiexperimentproject.recyclerView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fearlessbear.uiexperimentproject.R;

/*
 * 负一屏快捷卡片展开动画实验
 */
public class ExpandedFollowAnimActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ABOVE_POS = 0;
    public static final int BELOW_POS = 6;

    private ListView mTargetAboveStartList;
    private ListView mTargetBelowStartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_follow_anim);
        mTargetAboveStartList = (ListView) findViewById(R.id.targetAboveStartList);
        mTargetAboveStartList.setAdapter(new TargetAboveStartAdapter());
        mTargetBelowStartList = (ListView) findViewById(R.id.targetBelowStartList);
        mTargetBelowStartList.setAdapter(new TargetBelowStartAdapter());

        findViewById(R.id.targetAboveStartBtn).setOnClickListener(this);
        findViewById(R.id.targetBelowStartBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.targetAboveStartBtn:
                intent = ExpandedFollowAnimImplActivity.acquireIntent(this, getTargetY(mTargetAboveStartList), true);
                break;
            case R.id.targetBelowStartBtn:
                intent = ExpandedFollowAnimImplActivity.acquireIntent(this, getTargetY(mTargetBelowStartList), false);
                break;
        }
        startActivity(intent);
    }

    private int getTargetY(ListView listView) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            View child = listView.getChildAt(i);
            if (child.getId() == R.id.target) {
                return child.getTop();
            }
        }
        Toast.makeText(this, "目标值无效", Toast.LENGTH_LONG).show();
        return 0;
    }

    abstract class MeasureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 40;
        }

        @Override
        public String getItem(int position) {
            return "二楼二楼二楼二楼二楼二楼";
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position == getTargetPosition() ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        position != getTargetPosition() ? android.R.layout.simple_list_item_2 : R.layout.expanded_follow_anim_sec_floor_target_item,
                        parent, false);
            }
            if (position != getTargetPosition()) {
                ((TextView) convertView.findViewById(android.R.id.text2)).setText(getItem(position) + " " + position);
            }
            return convertView;
        }

        protected abstract int getTargetPosition();

    }

    class TargetBelowStartAdapter extends MeasureAdapter {
        @Override
        protected int getTargetPosition() {
            return BELOW_POS;
        }
    }

    class TargetAboveStartAdapter extends MeasureAdapter {
        @Override
        protected int getTargetPosition() {
            return ABOVE_POS;
        }
    }
}
