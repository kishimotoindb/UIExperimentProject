package com.fearlessbear.uiexperimentproject.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fearlessbear.uiexperimentproject.R;

/*
 * 负一屏快捷卡片展开动画实验
 */
public class ExpandedFollowAnimImplActivity extends AppCompatActivity {

    private ListView mListView;
    private boolean mTargetAboveStart;

    public static Intent acquireIntent(Context context, int targetY, boolean targetAboveStart) {
        Intent intent = new Intent(context, ExpandedFollowAnimImplActivity.class);
        intent.putExtra("targetAboveStart", targetAboveStart);
        intent.putExtra("targetY", targetY);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_follow_anim_impl);
        int targetY = getIntent().getIntExtra("targetY", 0);
        mTargetAboveStart = getIntent().getBooleanExtra("targetAboveStart", false);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new OuterAdapter(targetY));
        ViewGroup header = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.expanded_follow_anim_list_header, mListView, false);
        ((TextView) header.findViewById(R.id.title)).setText(mTargetAboveStart ? "目标在起始位置上面" : "目标在起始位置下方");
        mListView.addHeaderView(header);
    }

    class OuterAdapter extends BaseAdapter {
        int mTargetY;

        public OuterAdapter(int targetY) {
            mTargetY = targetY;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Integer getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        position != 0 ? android.R.layout.simple_list_item_1 : R.layout.expanded_follow_anim_expanded_view_item,
                        parent, false);
                if (convertView instanceof ExpandView) {
                    final ExpandView expandedView = (ExpandView) convertView;
                    expandedView.setTargetViewTargetY(mTargetY);
                    expandedView.setTargetAboveStart(mTargetAboveStart);
                    expandedView.setListView(mListView);
                    ListView secList = convertView.findViewById(R.id.second_floor);
                    secList.setAdapter(new SecAdapter(expandedView));
                }
            }
            if (position > 0) {
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(position + "");
            }
            return convertView;
        }
    }

    class SecAdapter extends BaseAdapter {
        ExpandView mExpandedView;

        SecAdapter(ExpandView expandedView) {
            mExpandedView = expandedView;
        }

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
            return position == getPosition() ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(
                        position != getPosition() ? android.R.layout.simple_list_item_2 : R.layout.expanded_follow_anim_sec_floor_target_item,
                        parent, false);
            }
            if (position != getPosition()) {
                convertView.getLocationOnScreen(location);
                ((TextView) convertView.findViewById(android.R.id.text2)).setText(getItem(position) + " " + position);
            }
            return convertView;
        }

        int getPosition() {
            return mExpandedView.isTargetAboveStart() ? ExpandedFollowAnimActivity.ABOVE_POS : ExpandedFollowAnimActivity.BELOW_POS;
        }

        int[] location = new int[2];
    }
}
